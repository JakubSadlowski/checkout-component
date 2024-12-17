package org.js.checkoutcomponent.service.checkout.db;

import lombok.Data;

import java.util.Date;

@Data
public class ItemEntity {
    private int itId;
    private String itName;
    private double itNormalPrice;
    private Date itCreateDate;
    private Date itUpdateDate;
}
