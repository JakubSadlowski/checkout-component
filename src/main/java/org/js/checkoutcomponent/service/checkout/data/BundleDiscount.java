package org.js.checkoutcomponent.service.checkout.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BundleDiscount {
    private String firstItemID;
    private String secondItemID;
    private BigDecimal discount;
}
