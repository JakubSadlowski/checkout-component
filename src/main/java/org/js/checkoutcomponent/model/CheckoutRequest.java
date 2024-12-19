package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class CheckoutRequest {
    @NotNull
    @Valid
    @Schema(name = "items", description = "List of items in the cart")
    private List<CartItem> items;
}
