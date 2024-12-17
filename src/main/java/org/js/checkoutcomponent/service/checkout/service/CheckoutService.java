package org.js.checkoutcomponent.service.checkout.service;

import org.js.checkoutcomponent.service.checkout.data.Item;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    private double calculateItemPrice(Item item, int quantity) {
        int specialPriceGroups = quantity / item.getRequiredQuantity();
        int remainingItems = quantity % item.getRequiredQuantity();

        return (specialPriceGroups * item.getSpecialPrice() * item.getRequiredQuantity()) +
                (remainingItems * item.getNormalPrice());
    }
}
