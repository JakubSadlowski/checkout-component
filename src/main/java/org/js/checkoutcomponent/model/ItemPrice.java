package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemPrice {
    @NotNull
    @Schema(name = "itemId", example = "A", description = "Product identifier")
    private String itemId;
    @NotNull
    @Schema(name = "quantity", example = "2", description = "Number of items")
    private int quantity;
    private double price;
}
