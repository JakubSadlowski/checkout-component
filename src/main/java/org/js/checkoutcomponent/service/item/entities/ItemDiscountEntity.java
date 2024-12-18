package org.js.checkoutcomponent.service.item.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ItemDiscountEntity {
    private String id;
    private String itemId;
    private int quantity;
    private BigDecimal specialPrice;
    @Builder.Default
    private LocalDate validFrom = LocalDate.now();
    private LocalDate validTo;
}
