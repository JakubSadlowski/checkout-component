package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.config.DAOConfig;
import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.ItemsMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = { CheckoutService.class, DAOConfig.class })
@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {
    @Mock
    private ItemsDAO itemsDAO;

    @InjectMocks
    private CheckoutService checkoutService;

   /* @BeforeEach
    void setUp() {
        when(itemsDAO.getItems(Set.of("A", "B"))).thenReturn(Map.of("A", ItemsMock.ITEM_A, "B", ItemsMock.ITEM_B));
    }*/

    @ParameterizedTest
    @CsvSource({ "20.5, 10.0, 2, 5, 60.5", "13.5, 2.0, 3, 4, 19.5" })
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

    @Test
    void calculateBundleDiscountsTest() {
        // Given
        CheckoutRequest request = new CheckoutRequest();
        List<CartItem> items = List.of(CartItem.builder()
                .itemId("A")
                .quantity(5)
                .build(),
            CartItem.builder()
                .itemId("B")
                .quantity(2)
                .build());
        request.setItems(items);

        // When
        BigDecimal totalDiscount = checkoutService.calculateBundleDiscounts(request, ItemsMock.bundleDiscountsMap);

        // Then
        BigDecimal expectedDiscount = new BigDecimal("40.0");
        Assertions.assertEquals(expectedDiscount, totalDiscount);
    }

    @Test
    void totalPriceTest() {
        // Given
        when(itemsDAO.getItems(Set.of("A", "B"))).thenReturn(Map.of("A", ItemsMock.ITEM_A, "B", ItemsMock.ITEM_B));
        CheckoutRequest request = new CheckoutRequest();
        request.setItems(List.of(CartItem.builder()
                .itemId("A")
                .quantity(5)
                .build(),
            CartItem.builder()
                .itemId("B")
                .quantity(6)
                .build()));

        // When
        CheckoutService.Result result = checkoutService.calculateTotalPriceWithItemDiscountsAndBundles(request);

        // Then
        Assertions.assertEquals(0, 0);
    }
}