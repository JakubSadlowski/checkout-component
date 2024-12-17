package org.js.checkoutcomponent.model;

import lombok.Data;

@Data
public class ItemPrice {
    private String itemId;
    private int quantity;
    private double price;
}
