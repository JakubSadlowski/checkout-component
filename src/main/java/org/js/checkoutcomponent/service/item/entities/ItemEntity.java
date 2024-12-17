package org.js.checkoutcomponent.service.item.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ItemEntity {
    private String id;
    private String name;
    private BigDecimal normalPrice;
    private Date createDate;
    private Date updateDate;
}
