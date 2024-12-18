package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CheckoutRequest {
    @NotNull
    @Valid
    @Schema(name = "items", description = "List of items in the cart", required = true)
    private List<CartItem> items;
}
