package org.js.checkoutcomponent.service.item.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ItemEntity {
    private String id;
    private String name;
    private BigDecimal normalPrice;
    @Builder.Default
    private LocalDate createDate = LocalDate.now();
    @Builder.Default
    private LocalDate updateDate = LocalDate.now();
}
