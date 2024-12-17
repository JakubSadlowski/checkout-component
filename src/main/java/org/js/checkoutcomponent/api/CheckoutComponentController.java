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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("checkout-component")
@Validated
@Tag(name = "checkout", description = "Checkout API")
public class CheckoutComponentController {

    @Operation(
            operationId = "calculateTotal",
            summary = "Calculate total price for cart items",
            description = "Calculates the total price considering bulk discounts and bundle offers"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful price calculation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutResponse.class))),
            /*@ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculateTotal400Response.class)))*/
    })
    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> calculateTotal(
            @Parameter(description = "Checkout request with items", required = true)
            @Valid @RequestBody CheckoutRequest checkoutRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
