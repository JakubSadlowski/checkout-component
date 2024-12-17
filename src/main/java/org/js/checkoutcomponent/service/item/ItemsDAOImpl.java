package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ItemsDAOImpl implements ItemsDAO {

    @Override
    public List<ItemEntity> getItems(List<String> itemIds) {
        return List.of();
    }

    @Override
    public List<ItemDiscountEntity> getItemDiscounts(String itemId) {
        return List.of();
    }

    @Override
    public List<BundleDiscountEntity> getBundleDiscounts(String bundleId) {
        return List.of();
    }

    private List<ItemEntity> getAllItems() {
        return List.of(ItemEntity.builder().id("A").name("Perfumes").normalPrice(new BigDecimal("40.0")).createDate(new Date()).updateDate(new Date()).build(),
                ItemEntity.builder().id("B").name("Soap").normalPrice(new BigDecimal("10.0")).createDate(new Date()).updateDate(new Date()).build(),
                ItemEntity.builder().id("C").name("Shampoo").normalPrice(new BigDecimal("30.0")).createDate(new Date()).updateDate(new Date()).build(),
                ItemEntity.builder().id("D").name("Towel").normalPrice(new BigDecimal("25.0")).createDate(new Date()).updateDate(new Date()).build());
    }
}
