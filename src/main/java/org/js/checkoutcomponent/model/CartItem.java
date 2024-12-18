package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CartItem {
    @NotNull
    @Schema(name = "itemId", example = "A", description = "Product identifier")
    private String itemId;

    @NotNull @Min(1)
    @Schema(name = "quantity", example = "3", description = "Number of items")
    private int quantity;
}
