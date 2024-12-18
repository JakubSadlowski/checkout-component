package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.model.ItemPrice;
import org.js.checkoutcomponent.service.checkout.data.Item;
import org.js.checkoutcomponent.service.checkout.mapper.ItemAndBundlesMapper;
import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CheckoutService {
    @Autowired
    private ItemsDAO itemsDAO;

    public CheckoutResponse calculateTotalPrice(CheckoutRequest request) {

        Result result = calculateTotalPriceWithItemDiscounts(request);

        CheckoutResponse response = new CheckoutResponse();

        response.setTotalPrice(result.totalPrice());
        response.setItemPrices(result.itemPrices());
        return response;
    }

    Result calculateTotalPriceWithItemDiscounts(CheckoutRequest request) {
        List<ItemPrice> itemPrices = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Set<String> itemIds = request.getItems().stream().map(CartItem::getItemId).collect(Collectors.toSet());
        Map<String, ItemEntity> itemsMap = itemsDAO.getItems((itemIds));
        Map<String, ItemDiscountEntity> discountsMap = itemsDAO.getItemDiscounts((itemIds));

        for (CartItem cartItem : request.getItems()) {
            Item item = mapDAOToItem(cartItem, itemsMap, discountsMap);

            BigDecimal itemTotal = calculateItemPrice(item, cartItem.getQuantity());
            BigDecimal newTotalPrice = totalPrice.add(itemTotal);

            ItemPrice itemPrice = new ItemPrice();
            itemPrice.setItemId(cartItem.getItemId());
            itemPrice.setQuantity(cartItem.getQuantity());
            itemPrice.setPrice(newTotalPrice);
            itemPrices.add(itemPrice);
        }

        return new Result(itemPrices, totalPrice);
    }

    static Item mapDAOToItem(CartItem cartItem, Map<String, ItemEntity> itemsMap, Map<String, ItemDiscountEntity> discountsMap) {
        ItemEntity itemDAO = itemsMap.get(cartItem.getItemId());
        ItemDiscountEntity itemDiscountDAO = discountsMap.get(cartItem.getItemId());
        return ItemAndBundlesMapper.mapFromDao(itemDAO, itemDiscountDAO);
    }

    BigDecimal calculateItemPrice(Item item, int quantity) {
        return calculateItemPrice(item.getNormalPrice(), item.getSpecialPrice(), item.getRequiredQuantity(), quantity);
    }

    BigDecimal calculateItemPrice(BigDecimal normalPrice, BigDecimal specialPrice, int requiredQuantity, int quantity) {
        int specialPriceGroups = quantity / requiredQuantity;
        int remainingItems = quantity % requiredQuantity;

        return specialPrice.multiply(BigDecimal.valueOf(requiredQuantity))
            .multiply(BigDecimal.valueOf(specialPriceGroups))
            .add(normalPrice.multiply(BigDecimal.valueOf(remainingItems)));
    }

    record Result(List<ItemPrice> itemPrices, BigDecimal totalPrice) {
    }
}
