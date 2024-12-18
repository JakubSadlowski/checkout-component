package org.js.checkoutcomponent.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CheckoutResponse {
    @Schema(name = "totalPrice", example = "90.0", description = "Total price after all discounts")
    private BigDecimal totalPrice;
    @Valid
    @Schema(name = "itemPrices", description = "Detailed breakdown of prices per item")
    private List<ItemPrice> itemPrices;
    @Schema(name = "bundleDiscountApplied", example = "5.0", description = "Total bundle discounts applied")
    private BigDecimal bundleDiscountApplied;
}
