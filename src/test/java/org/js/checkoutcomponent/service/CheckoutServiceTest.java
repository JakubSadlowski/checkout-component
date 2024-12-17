package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.config.DAOConfig;
import org.js.checkoutcomponent.service.checkout.data.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@SpringBootTest
@ContextConfiguration(classes = { CheckoutService.class, DAOConfig.class })
class CheckoutServiceTest {
    @Autowired
    private CheckoutService checkoutService;

    @ParameterizedTest
    @CsvSource({
        "20.5, 10.0, 2, 5, 60.5",
        "13.5, 2.0, 3, 4, 19.5"
    })
    void calculateItemPriceTest(String normalPrice, String specialPrice, String requiredQuantity, String quantity, String expectedTotalPrice) {
        // Given
        BigDecimal normalPriceConverted = new BigDecimal(normalPrice);
        BigDecimal specialPriceConverted = new BigDecimal(specialPrice);
        int requiredQuantityConverted = Integer.parseInt(requiredQuantity);
        int quantityConverted = Integer.parseInt(quantity);
        BigDecimal expectedTotalPriceConverted = new BigDecimal(expectedTotalPrice);

        // When
        BigDecimal totalPrice = checkoutService.calculateItemPrice(normalPriceConverted, specialPriceConverted, requiredQuantityConverted, quantityConverted);

        // Then
        Assertions.assertEquals(expectedTotalPriceConverted, totalPrice);
    }
}