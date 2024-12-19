package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.config.CheckoutConfig;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = { CheckoutConfig.class })
class ItemsDAOImplTest {
    @Autowired
    ItemsDAO dao;

    @ParameterizedTest
    @CsvSource({ "A, C", "B, C" })
    void getItemsTest(String itemId1, String itemId2) {
        // Given
        Set<String> itemIds = Set.of(itemId1, itemId2);

        // When
        Map<String, ItemEntity> items = dao.getItems(itemIds);

        // Then
        assertEquals(2, items.size());
        ItemEntity itemA = items.get(itemId1);
        ItemEntity expectedItem1 = ItemsMock.itemsMap.get(itemId1);
        assertEquals(expectedItem1, itemA);
        ItemEntity itemC = items.get(itemId2);
        ItemEntity expectedItem2 = ItemsMock.itemsMap.get(itemId2);
        assertEquals(expectedItem2, itemC);

    }

    @ParameterizedTest
    @CsvSource({ "A, B", "C, D" })
    void getDiscountsTest(String itemId1, String itemId2) {
        // Given
        Set<String> itemIds = Set.of(itemId1, itemId2);

        // When
        Map<String, ItemDiscountEntity> itemDiscounts = dao.getItemDiscounts(itemIds);

        // Then
        assertEquals(2, itemDiscounts.size());
        ItemDiscountEntity itemDiscountA = itemDiscounts.get(itemId1);
        ItemDiscountEntity expectedDiscount1 = ItemsMock.discountsMap.get(itemId1);
        assertEquals(expectedDiscount1, itemDiscountA);
        ItemDiscountEntity itemDiscountB = itemDiscounts.get(itemId2);
        ItemDiscountEntity expectedDiscount2 = ItemsMock.discountsMap.get(itemId2);
        assertEquals(expectedDiscount2, itemDiscountB);
    }

    @ParameterizedTest
    @CsvSource({ "A, B, 1", "C, D, 1", "A, C, 0", "B, D, 0" })
    void getBundlesTest(String itemId1, String itemId2, int expectedDiscountsCount) {
        // Given
        Set<String> itemIds = Set.of(itemId1, itemId2);

        // When
        Map<String, BundleDiscountEntity> bundleDiscounts = dao.getBundleDiscounts(itemIds);

        // Then
        assertEquals(expectedDiscountsCount, bundleDiscounts.size());
    }
}