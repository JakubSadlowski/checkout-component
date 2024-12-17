package org.js.checkoutcomponent.model;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutResponse {
    private double totalPrice;
    private List<ItemPrice> itemPrices;
    private double bundleDiscountApplied;
}
