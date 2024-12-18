package org.js.checkoutcomponent.api;

import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * TODO: Implement such tests
 * 1) Request with empty list of cart items => HttpStatus.OK
 * 2) Request with at least two CartItems in the list having the same ID => HttpStatus.BAD_REQUEST
 * 3) Request with at least one CartItem having quantity =0 => HttpStatus.BAD_REQUEST
 * 4) Request with at least one CartItem having quantity <0 => HttpStatus.BAD_REQUEST
 * 5) Request with some cart Items which are valid => HttpStatus.OK
 */
@ExtendWith(MockitoExtension.class)
class CheckoutComponentControllerTest {
    @InjectMocks
    private CheckoutComponentController controller;

    @Disabled("TODO: To be fixed soon")
    @Test
    void calculateTotal_validRequest_returnsSuccessResponse() {
        // Given
        CheckoutRequest request = new CheckoutRequest();

        // When
        ResponseEntity<CheckoutResponse> response = controller.calculateTotal(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}