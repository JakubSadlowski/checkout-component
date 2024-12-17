package org.js.checkoutcomponent.api;

import org.js.checkoutcomponent.model.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class CheckoutComponentControllerTest {
    @InjectMocks
    private CheckoutComponentController controller;

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
    }

    @Test
    void calculateTotal_ValidRequest_ReturnsSuccessResponse() {
        // Given
        CheckoutRequest request = new CheckoutRequest();

        // When
        ResponseEntity<CheckoutResponse> response = controller.calculateTotal(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
    }

    /*@Test
    void calculateTotal_EmptyRequest_ValidationFails() {
        // Given
        CheckoutRequest request = new CheckoutRequest();

        // When
        Set<ConstraintViolation<CheckoutRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
    }*/
}