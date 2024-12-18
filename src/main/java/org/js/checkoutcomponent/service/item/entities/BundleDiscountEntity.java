package org.js.checkoutcomponent.service.item.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BundleDiscountEntity {
    private String id;
    private String fromItemId;
    private String toItemId;
    private BigDecimal discountPrice;
    @Builder.Default
    private LocalDate validFrom = LocalDate.now();
    private LocalDate validTo;
}
