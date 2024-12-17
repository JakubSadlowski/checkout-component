package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemsDAOImpl implements ItemsDAO {

    @Override
    public Map<String, ItemEntity> getItems(Set<String> itemIds) {
        return getAllItems().stream()
            .filter(e -> itemIds.contains(e.getId()))
            .collect(Collectors.toMap(ItemEntity::getId, item -> item));
    }

    @Override
    public List<ItemDiscountEntity> getItemDiscounts(Set<String> itemIds) {
        return getAllItemDiscounts().stream()
            .filter(e -> itemIds.contains(e.getItemId()))
            .toList();
    }

    @Override
    public List<BundleDiscountEntity> getBundleDiscounts(Set<String> itemIds) {
        return List.of();
    }

    private List<ItemEntity> getAllItems() {
        return List.of(ItemsMockedRepository.ITEM_A, ItemsMockedRepository.ITEM_B, ItemsMockedRepository.ITEM_C, ItemsMockedRepository.ITEM_D, ItemsMockedRepository.ITEM_E);
    }

    private List<ItemDiscountEntity> getAllItemDiscounts() {
        return List.of(ItemsMockedRepository.DISCOUNT_A, ItemsMockedRepository.DISCOUNT_B, ItemsMockedRepository.DISCOUNT_C, ItemsMockedRepository.DISCOUNT_D);
    }

    private List<BundleDiscountEntity> getAllBundleDiscounts() {
        return List.of(BundleDiscountEntity.builder()
                .id("1")
                .fromItemId("A")
                .toItemId("B")
                .discountPrice(new BigDecimal("20.0"))
                .validFrom(LocalDate.of(2024, 02, 15))
                .build(),
            BundleDiscountEntity.builder()
                .id("2")
                .fromItemId("C")
                .toItemId("D")
                .discountPrice(new BigDecimal("10.0"))
                .validFrom(LocalDate.of(2024, 01, 11))
                .build());
    }
}
