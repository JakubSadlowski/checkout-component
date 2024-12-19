package org.js.checkoutcomponent.service;

import lombok.extern.apachecommons.CommonsLog;
import org.js.checkoutcomponent.errors.CartItemNotFoundException;
import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.model.ItemPrice;
import org.js.checkoutcomponent.service.checkout.data.Item;
import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CommonsLog
public class CheckoutService {
    private ItemsDAO itemsDAO;

    @Autowired
    public CheckoutService(ItemsDAO itemsDAO) {
        this.itemsDAO = itemsDAO;
    }

    public CheckoutResponse calculateTotalPrice(CheckoutRequest request) {
        log.info("Checkout component 3.0 request: " + request);
        Result result = calculateTotalPriceWithItemDiscountsAndBundles(request);

        CheckoutResponse response = CheckoutResponse.builder()
            .totalPrice(result.totalPrice())
            .itemPrices(result.itemPrices())
            .bundleDiscountApplied(result.bundleDiscount())
            .build();

        log.info("Response: " + response);

        return response;
    }

    Result calculateTotalPriceWithItemDiscountsAndBundles(CheckoutRequest request) {
        Set<String> itemIds = getRequestItemsIds(request);

        List<ItemPrice> itemPrices = calculateItemsPricesWithDiscounts(request.getItems(), itemIds);
        BigDecimal totalPrice = calculateTotalPriceForItemsPricesWithDiscounts(itemPrices);
        BigDecimal totalBundleDiscount = calculateBundleDiscounts(request, itemsDAO.getBundleDiscounts(itemIds));

        totalPrice = totalPrice.subtract(totalBundleDiscount);

        return new Result(itemPrices, totalPrice, totalBundleDiscount);
    }

    private List<ItemPrice> calculateItemsPricesWithDiscounts(List<CartItem> cartItems, Set<String> itemIds) {
        List<ItemPrice> itemPrices = new ArrayList<>();

        Map<String, Item> itemWithDiscounts = getItemsWithDiscounts(itemIds);

        for (CartItem cartItem : cartItems) {
            Item itemIncludingDiscount = itemWithDiscounts.get(cartItem.getItemId());

            BigDecimal calculatedPrice = calculateItemPrice(itemIncludingDiscount, cartItem.getQuantity());

            itemPrices.add(ItemPrice.builder()
                .itemId(cartItem.getItemId())
                .quantity(cartItem.getQuantity())
                .price(calculatedPrice)
                .build());
        }

        log.info("Fetched items with discounts: " + Arrays.toString(itemPrices.toArray()));

        return itemPrices;
    }

    private Map<String, Item> getItemsWithDiscounts(Set<String> itemIds) {
        Map<String, Item> itemsWithDiscounts = new HashMap<>();
        Map<String, ItemEntity> items = itemsDAO.getItems(itemIds);
        validateIfAllInputItemsFoundInDatabase(itemIds, items.keySet());

        Map<String, ItemDiscountEntity> itemDiscounts = itemsDAO.getItemDiscounts(itemIds);
        for (Map.Entry<String, ItemEntity> entry : items.entrySet()) {
            ItemEntity itemEntity = entry.getValue();
            ItemDiscountEntity discount = itemDiscounts.get(itemEntity.getId());
            Item itemWithDiscount = Item.builder()
                .id(itemEntity.getId())
                .normalPrice(itemEntity.getNormalPrice())
                .specialPrice(discount != null ? discount.getSpecialPrice() : null)
                .requiredQuantity(discount != null ? discount.getQuantity() : 0)
                .build();

            itemsWithDiscounts.put(entry.getKey(), itemWithDiscount);
        }
        return itemsWithDiscounts;
    }

    private void validateIfAllInputItemsFoundInDatabase(Set<String> itemIds, Set<String> fetchedItemIds) {
        for (String itemId : itemIds) {
            if (!fetchedItemIds.contains(itemId)) {
                throw new CartItemNotFoundException(String.format("Cart item %s not found in database.", itemId));
            }
        }
    }

    BigDecimal calculateItemPrice(Item item, int quantity) {
        if (item.getRequiredQuantity() == 0 || item.getSpecialPrice() == null) {
            return calculateItemPriceWithoutDiscount(item.getNormalPrice(), quantity);
        }
        return calculateItemPrice(item.getNormalPrice(), item.getSpecialPrice(), item.getRequiredQuantity(), quantity);
    }

    BigDecimal calculateItemPrice(BigDecimal normalPrice, BigDecimal specialPrice, int requiredQuantity, int quantity) {
        int specialPriceGroups = quantity / requiredQuantity;
        int remainingItems = quantity % requiredQuantity;

        return specialPrice.multiply(BigDecimal.valueOf(requiredQuantity))
            .multiply(BigDecimal.valueOf(specialPriceGroups))
            .add(normalPrice.multiply(BigDecimal.valueOf(remainingItems)));
    }

    BigDecimal calculateItemPriceWithoutDiscount(BigDecimal normalPrice, int quantity) {
        return normalPrice.multiply(BigDecimal.valueOf(quantity));
    }

    BigDecimal calculateBundleDiscounts(CheckoutRequest request, Map<String, BundleDiscountEntity> bundleDiscountsMap) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        Map<String, Integer> itemQuantities = getItemsQuantities(request);

        for (BundleDiscountEntity discount : bundleDiscountsMap.values()) {
            totalDiscount = totalDiscount.add(calculateSingleBundleDiscount(discount, itemQuantities));
        }

        return totalDiscount;
    }

    private static BigDecimal calculateSingleBundleDiscount(BundleDiscountEntity discount, Map<String, Integer> itemQuantities) {
        int firstItemQuantity = itemQuantities.getOrDefault(discount.getFromItemId(), 0);
        int secondItemQuantity = itemQuantities.getOrDefault(discount.getToItemId(), 0);
        if (firstItemQuantity == 0 || secondItemQuantity == 0) {
            return BigDecimal.ZERO;
        }

        int pairs = Math.min(firstItemQuantity, secondItemQuantity);
        return discount.getDiscountPrice()
            .multiply(BigDecimal.valueOf(pairs));
    }

    private static Map<String, Integer> getItemsQuantities(CheckoutRequest request) {
        return request.getItems()
            .stream()
            .collect(Collectors.toMap(CartItem::getItemId, CartItem::getQuantity));
    }

    private BigDecimal calculateTotalPriceForItemsPricesWithDiscounts(List<ItemPrice> itemPrices) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ItemPrice itemPrice : itemPrices) {
            totalPrice = totalPrice.add(itemPrice.getPrice());
        }
        return totalPrice;
    }

    private static Set<String> getRequestItemsIds(CheckoutRequest request) {
        return request.getItems()
            .stream()
            .map(CartItem::getItemId)
            .collect(Collectors.toSet());
    }

    record Result(List<ItemPrice> itemPrices, BigDecimal totalPrice, BigDecimal bundleDiscount) {
    }
}
