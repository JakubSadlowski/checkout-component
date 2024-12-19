package org.js.checkoutcomponent.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.apachecommons.CommonsLog;
import org.js.checkoutcomponent.errors.CartItemNotFoundException;
import org.js.checkoutcomponent.errors.CartItemWithZeroOrLessQuantity;
import org.js.checkoutcomponent.errors.InvalidRequestException;
import org.js.checkoutcomponent.errors.ServiceGeneralException;
import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.model.FailureCartItemNotFoundResponse;
import org.js.checkoutcomponent.model.FailureResponse;
import org.js.checkoutcomponent.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@Validated
@CommonsLog
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
        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cart item not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureCartItemNotFoundResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FailureResponse.class))) })
    @PostMapping("/checkout/v3")
    public ResponseEntity<CheckoutResponse> calculateTotal(@Parameter(description = "Checkout request with items", required = true) @Valid @RequestBody CheckoutRequest checkoutRequest) {
        calculateTotalInputValidation(checkoutRequest);
        try {
            return ResponseEntity.ok(checkoutService.calculateTotalPrice(checkoutRequest));
        } catch (CartItemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceGeneralException("Unable to perform operation. Details: " + e.getMessage(), e);
        }
    }

    private void calculateTotalInputValidation(CheckoutRequest checkoutRequest) {
        if (hasRequestDuplicatedItems(checkoutRequest)) {
            throw new InvalidRequestException("Some cart items are duplicated");
        } else if (hasItemWithZeroOrLessQuantity(checkoutRequest)) {
            throw new CartItemWithZeroOrLessQuantity("Some quantity of cart items is less than or equal to 0");
        }
    }

    private boolean hasRequestDuplicatedItems(@Valid CheckoutRequest checkoutRequest) {
        List<CartItem> items = checkoutRequest.getItems();
        int numberOfItems = items.size();
        int numberOfUniqueItems = items.stream()
            .map(CartItem::getItemId)
            .collect(Collectors.toSet())
            .size();
        return numberOfItems != numberOfUniqueItems;
    }

    private boolean hasItemWithZeroOrLessQuantity(@Valid CheckoutRequest checkoutRequest) {
        List<CartItem> items = checkoutRequest.getItems();
        return items.stream()
            .anyMatch(item -> item.getQuantity() <= 0);
    }
}
