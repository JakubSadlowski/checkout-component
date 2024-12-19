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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO: Implement such tests
 * 1) Request with empty list of cart items => HttpStatus.OK
 * 2) Request with at least two CartItems in the list having the same ID => HttpStatus.BAD_REQUEST - DONE
 * 3) Request with at least one CartItem having quantity =0 => HttpStatus.BAD_REQUEST
 * 4) Request with at least one CartItem having quantity <0 => HttpStatus.BAD_REQUEST
 * 5) Request with some cart Items which are valid => HttpStatus.OK - DONE
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(classes = { CheckoutConfig.class, CheckoutComponentController.class })
@ComponentScan(basePackages = { "org.js.checkoutcomponent.errors" })
class CheckoutComponentControllerTest {
    @Autowired
    private CheckoutComponentController controller;

    @Autowired
    private TestRestTemplate restTemplate;

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
        assertEquals(new BigDecimal("230.0"), checkoutResponse.getTotalPrice());
        assertEquals(new BigDecimal("0"), checkoutResponse.getBundleDiscountApplied());
        List<ItemPrice> itemPrices = checkoutResponse.getItemPrices();
        assertEquals(2, itemPrices.size());
        ItemPrice itemPriceA = itemPrices.get(0);
        assertEquals("A", itemPriceA.getItemId());
        assertEquals(new BigDecimal("170.0"), itemPriceA.getPrice());
        assertEquals(5, itemPriceA.getQuantity());
        ItemPrice itemPriceC = itemPrices.get(1);
        assertEquals("C", itemPriceC.getItemId());
        assertEquals(new BigDecimal("60.0"), itemPriceC.getPrice());
        assertEquals(2, itemPriceC.getQuantity());
    }

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
        //ResponseEntity<CheckoutResponse> response = controller.calculateTotal(request);
        ResponseEntity<Map> response = restTemplate.postForEntity(
            "/api/checkout",
            request,
            Map.class
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        /*@SuppressWarnings("unchecked")
        Map<String, Object> body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.containsKey("message")).isTrue();
        assertThat(body.containsKey("timestamp")).isTrue();
        assertThat(body.get("message")).isEqualTo("Some cart items are duplicated");*/
        /*assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);*/
    }



}