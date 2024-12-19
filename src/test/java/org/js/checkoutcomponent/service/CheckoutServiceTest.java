package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.config.CheckoutConfig;
import org.js.checkoutcomponent.model.CartItem;
import org.js.checkoutcomponent.model.CheckoutRequest;
import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.ItemsMock;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.junit.jupiter.api.Assertions;
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
@ContextConfiguration(classes = { CheckoutService.class, CheckoutConfig.class })
@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {
    @Mock
    private ItemsDAO itemsDAO;

    @InjectMocks
    private CheckoutService checkoutService;

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
        request.setItems(List.of(CartItem.builder()
                .itemId("A")
                .quantity(5)
                .build(),
            CartItem.builder()
                .itemId("B")
                .quantity(2)
                .build()));
        Map<String, BundleDiscountEntity> bundleDiscounts = ItemsMock.bundleDiscountsMap;

        // When
        BigDecimal totalDiscount = checkoutService.calculateBundleDiscounts(request, bundleDiscounts);

        // Then
        BigDecimal expectedDiscount = new BigDecimal("40.0");
        Assertions.assertEquals(expectedDiscount, totalDiscount);
    }

    @Test
    void totalPriceTest() {
        // Given
        Set<String> inputItemsIds = Set.of("A", "B");
        when(itemsDAO.getItems(inputItemsIds)).thenReturn(Map.of("A", ItemsMock.ITEM_A, "B", ItemsMock.ITEM_B));
        when(itemsDAO.getItemDiscounts(inputItemsIds)).thenReturn(Map.of("A", ItemsMock.DISCOUNT_A, "B", ItemsMock.DISCOUNT_B));
        when(itemsDAO.getBundleDiscounts(inputItemsIds)).thenReturn(Map.of("A", ItemsMock.BUNDLE_A_B, "B", ItemsMock.BUNDLE_C_D));

        CheckoutRequest request = new CheckoutRequest();
        request.setItems(List.of(new CartItem("A", 5), new CartItem("B", 6)));

        // When
        CheckoutService.Result result = checkoutService.calculateTotalPriceWithItemDiscountsAndBundles(request);

        // Then
        Assertions.assertEquals(new BigDecimal("115.0"), result.totalPrice());
        Assertions.assertEquals(new BigDecimal("100.0"), result.bundleDiscount());
        Assertions.assertEquals(2,
            result.itemPrices()
                .size());
    }

    @Test
    void checkForItemWithoutDiscountTest() {
        // Given
        Set<String> inputItemsIds = Set.of("A", "B");
        when(itemsDAO.getItems(inputItemsIds)).thenReturn(Map.of("A", ItemsMock.ITEM_A, "B", ItemsMock.ITEM_B));
        when(itemsDAO.getItemDiscounts(inputItemsIds)).thenReturn(Map.of("A", ItemsMock.DISCOUNT_A));

        CheckoutRequest request = new CheckoutRequest();
        request.setItems(List.of(new CartItem("A", 5), new CartItem("B", 6)));

        // When
        CheckoutService.Result result = checkoutService.calculateTotalPriceWithItemDiscountsAndBundles(request);

        // Then
        Assertions.assertEquals(new BigDecimal("230.0"), result.totalPrice());
        Assertions.assertEquals(new BigDecimal("0"), result.bundleDiscount());
        Assertions.assertEquals(2,
            result.itemPrices()
                .size());
    }
}