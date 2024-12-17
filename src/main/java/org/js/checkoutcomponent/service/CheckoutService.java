package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.model.ItemPrice;
import org.js.checkoutcomponent.service.checkout.data.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService {
    public CheckoutResponse calculateTotalPrice(CheckoutRequest request) {
        CheckoutResponse response = new CheckoutResponse();
        List<ItemPrice> itemPrices = new ArrayList<>();
        double totalPrice = 0;

        for (CartItem cartItem : request.getItems()) {
            //FIXME Need to add dataBase implementation
            Item item = new Item();
            if (item == null) {
                throw new IllegalArgumentException("Invalid item ID: " + cartItem.getItemId());
            }

            double itemTotal = calculateItemPrice(item, cartItem.getQuantity());
            totalPrice += itemTotal;

            ItemPrice itemPrice = new ItemPrice();
            itemPrice.setItemId(cartItem.getItemId());
            itemPrice.setQuantity(cartItem.getQuantity());
            itemPrice.setPrice(itemTotal);
            itemPrices.add(itemPrice);
        }

        response.setTotalPrice(totalPrice);
        response.setItemPrices(itemPrices);
        return response;
    }

    private double calculateItemPrice(Item item, int quantity) {
        int specialPriceGroups = quantity / item.getRequiredQuantity();
        int remainingItems = quantity % item.getRequiredQuantity();

        return (specialPriceGroups * item.getSpecialPrice() * item.getRequiredQuantity()) +
                (remainingItems * item.getNormalPrice());
    }
}
