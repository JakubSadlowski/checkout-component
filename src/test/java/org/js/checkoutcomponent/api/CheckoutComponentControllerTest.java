package org.js.checkoutcomponent.api;

import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.model.CheckoutResponse;
import org.js.checkoutcomponent.model.ItemPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * TODO: Implement such tests
 * 1) Request with empty list of cart items => HttpStatus.OK
 * 2) Request with at least two CartItems in the list having the same ID => HttpStatus.BAD_REQUEST
 * 3) Request with at least one CartItem having quantity =0 => HttpStatus.BAD_REQUEST
 * 4) Request with at least one CartItem having quantity <0 => HttpStatus.BAD_REQUEST
 * 5) Request with some cart Items which are valid => HttpStatus.OK
 */
@SpringBootTest
//@ContextConfiguration(classes = { CheckoutConfig.class, CheckoutComponentController.class })
@ComponentScan(basePackages = { "org.js.checkoutcomponent.errors" })
    //@ExtendWith(MockitoExtension.class)
class CheckoutComponentControllerTest {
    @Autowired
    private CheckoutComponentController controller;

    @Test
    void checkoutValidRequest_returnsSuccessResponse() {
        // Given
        CheckoutRequest request = new CheckoutRequest();
        request.setItems(List.of(CartItem.builder()
                .itemId("A")
                .quantity(5)
                .build(),
            CartItem.builder()
                .itemId("C")
                .quantity(2)
                .build()));

        // When
        ResponseEntity<CheckoutResponse> response = controller.calculateTotal(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        CheckoutResponse checkoutResponse = response.getBody();
        Assertions.assertNotNull(checkoutResponse);
        Assertions.assertEquals(new BigDecimal("230.0"), checkoutResponse.getTotalPrice());
        Assertions.assertEquals(new BigDecimal("0"), checkoutResponse.getBundleDiscountApplied());
        List<ItemPrice> itemPrices = checkoutResponse.getItemPrices();
        Assertions.assertEquals(2, itemPrices.size());
        ItemPrice itemPriceA = itemPrices.get(0);
        Assertions.assertEquals("A", itemPriceA.getItemId());
        Assertions.assertEquals(new BigDecimal("170.0"), itemPriceA.getPrice());
        Assertions.assertEquals(5, itemPriceA.getQuantity());
        ItemPrice itemPriceC = itemPrices.get(1);
        Assertions.assertEquals("C", itemPriceC.getItemId());
        Assertions.assertEquals(new BigDecimal("60.0"), itemPriceC.getPrice());
        Assertions.assertEquals(2, itemPriceC.getQuantity());
    }

    @Disabled("FIXME: Issue with GlobalExceptionHandler")
    @Test
    void checkoutInvalidRequestWithTwoTheSameItems_returnsBadRequest() {
        // Given
        CheckoutRequest request = new CheckoutRequest();
        request.setItems(List.of(CartItem.builder()
                .itemId("A")
                .quantity(5)
                .build(),
            CartItem.builder()
                .itemId("B")
                .quantity(2)
                .build(),
            CartItem.builder()
                .itemId("A")
                .quantity(2)
                .build()));

        // When
        ResponseEntity<CheckoutResponse> response = controller.calculateTotal(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}