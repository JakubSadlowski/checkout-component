package org.js.checkoutcomponent.service.item.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemDiscountEntity {
    private String id;
    private String itemId;
    private int quantity;
    private BigDecimal specialPrice;
    private Date validFrom;
    private Date validTo;
}
