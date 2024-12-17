package org.js.checkoutcomponent.service.checkout.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private String id;
    private BigDecimal normalPrice;
    private BigDecimal specialPrice;
    private int requiredQuantity;
}
