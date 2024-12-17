package org.js.checkoutcomponent.service.checkout.data;

import lombok.Data;

@Data
public class BundleDiscount {
    private String firstItemID;
    private String secondItemID;
    private double discount;
}
