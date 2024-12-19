package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ItemPrice {
    @NotNull
    @Schema(name = "itemId", example = "A", description = "Product identifier")
    private String itemId;
    @NotNull
    @Schema(name = "quantity", example = "3", description = "Number of items")
    private int quantity;
    @NotNull
    @Schema(name = "price", example = "90.0", description = "Price of all the items combined with given identifier")
    private BigDecimal price;
}
