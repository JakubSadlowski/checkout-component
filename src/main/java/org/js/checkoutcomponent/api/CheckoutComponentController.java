package org.js.checkoutcomponent.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Validated
@Tag(name = "checkout", description = "Checkout API")
public class CheckoutComponentController {
    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutComponentController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Operation(operationId = "calculateTotal", summary = "Calculate total price for cart items", description = "Calculates the total price considering special prices and bundle discounts")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful price calculation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CheckoutResponse.class))),
            /*@ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculateTotal400Response.class)))*/ })
    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> calculateTotal(@Parameter(description = "Checkout request with items", required = true) @Valid @RequestBody CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(checkoutService.calculateTotalPrice(checkoutRequest));
    }
}
