package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.config.DAOConfig;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = { DAOConfig.class})
class ItemsDAOImplTest {
    @Autowired
    ItemsDAO dao;

    @ParameterizedTest
    @CsvSource({
        "A, C", "B, C"
    })
    void getItemsTest(String itemId1, String itemId2) {
        // Given
        Set<String> itemIds = Set.of(itemId1, itemId2);

        // When
        Map<String ,ItemEntity> items = dao.getItems(itemIds);

        // Then
        assertEquals(2, items.size());
        ItemEntity itemA = items.get(itemId1);
        ItemEntity expectedItem1 = ItemsMock.itemsMap.get(itemId1);
        assertEquals(expectedItem1, itemA);
        ItemEntity itemC = items.get(itemId2);
        ItemEntity expectedItem2 = ItemsMock.itemsMap.get(itemId2);
        assertEquals(expectedItem2, itemC);

    }

    @Test
    void getDiscountsTest() {
        // Given
        Set<String> itemIds = Set.of("A", "B");

        // When
        List<ItemDiscountEntity> itemDiscounts = dao.getItemDiscounts(itemIds);

        // Then
        assertEquals(2, itemDiscounts.size());
        ItemDiscountEntity itemDiscountA = itemDiscounts.get(0);
        assertEquals(ItemsMock.DISCOUNT_A, itemDiscountA);
        ItemDiscountEntity itemDiscountB = itemDiscounts.get(1);
        assertEquals(ItemsMock.DISCOUNT_B, itemDiscountB);
    }

}