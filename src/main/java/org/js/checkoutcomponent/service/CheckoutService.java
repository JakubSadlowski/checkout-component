package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.model.ItemPrice;
import org.js.checkoutcomponent.service.checkout.data.Item;
import org.js.checkoutcomponent.service.item.ItemsDAO;
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
        double totalPrice = 0;

        Set<String> itemIds = request.getItems().stream().map(e -> e.getItemId()).collect(Collectors.toSet());
        Map<String, ItemEntity> itemsMap = itemsDAO.getItems((itemIds));

        for (CartItem cartItem : request.getItems()) {
            //FIXME Need to add dataBase implementation
            ItemEntity item = itemsMap.get(cartItem.getItemId());
            if (item == null) {
                throw new IllegalArgumentException("Invalid item ID: " + cartItem.getItemId());
            }

            //BigDecimal itemTotal = calculateItemPrice(item, cartItem.getQuantity());
            /*totalPrice += itemTotal;

            ItemPrice itemPrice = new ItemPrice();
            itemPrice.setItemId(cartItem.getItemId());
            itemPrice.setQuantity(cartItem.getQuantity());
            itemPrice.setPrice(itemTotal);
            itemPrices.add(itemPrice);*/
        }
        Result result = new Result(itemPrices, totalPrice);
        return result;
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

    record Result(List<ItemPrice> itemPrices, double totalPrice) {
    }
}
