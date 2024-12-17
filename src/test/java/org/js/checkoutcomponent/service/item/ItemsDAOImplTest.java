package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.config.DAOConfig;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = { DAOConfig.class})
class ItemsDAOImplTest {
    @Autowired
    ItemsDAO dao;

    @Test
    void getItemsTest() {
        // Given
        Set<String> itemIds = Set.of("A", "C");

        // When
        List<ItemEntity> items = dao.getItems(itemIds);

        // Then
        assertEquals(2, items.size());
        ItemEntity itemA = items.get(0);
        assertEquals("A", itemA.getId());
        ItemEntity itemC = items.get(1);
        assertEquals("C", itemC.getId());
    }

}