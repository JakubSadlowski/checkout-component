package org.js.checkoutcomponent.service.checkout.data;

import lombok.Data;

@Data
public class Item {
    private String id;
    private double normalPrice;
    private double specialPrice;
    private int requiredQuantity;
}
