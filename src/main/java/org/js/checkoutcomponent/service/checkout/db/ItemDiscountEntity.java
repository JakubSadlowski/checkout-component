package org.js.checkoutcomponent.service.checkout.db;

import lombok.Data;

import java.util.Date;

@Data
public class ItemDiscountEntity {
    private int itdId;
    private int itId;
    private int itdQuantity;
    private double itdSpecialPrice;
    private Date itdValidFrom;
    private Date itdValidTo;
}
