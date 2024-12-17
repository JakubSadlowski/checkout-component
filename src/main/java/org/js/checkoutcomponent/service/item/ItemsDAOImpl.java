package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ItemsDAOImpl implements ItemsDAO {

    @Override
    public List<ItemEntity> getItems(Set<String> itemIds) {
        return getAllItems().stream()
            .filter(e -> itemIds.contains(e.getId()))
            .toList();
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
        return List.of(ItemsMockedRepository.ITEM_A, ItemsMockedRepository.ITEM_B, ItemsMockedRepository.ITEM_C, ItemsMockedRepository.ITEM_D, ItemsMockedRepository.ITEM_E);
    }

    private List<ItemDiscountEntity> getAllItemDiscounts() {
        return List.of(ItemDiscountEntity.builder()
                .id("1")
                .itemId("A")
                .specialPrice(new BigDecimal("30.0"))
                .quantity(3)
                .validFrom(LocalDate.of(2024, 01, 21))
                .build(),
            ItemDiscountEntity.builder()
                .id("2")
                .itemId("B")
                .specialPrice(new BigDecimal("7.5"))
                .quantity(3)
                .validFrom(LocalDate.of(2024, 05, 23))
                .build(),
            ItemDiscountEntity.builder()
                .id("3")
                .itemId("C")
                .specialPrice(new BigDecimal("20.0"))
                .quantity(3)
                .validFrom(LocalDate.of(2024, 01, 10))
                .build(),
            ItemDiscountEntity.builder()
                .id("4")
                .itemId("D")
                .specialPrice(new BigDecimal("23.5"))
                .quantity(3)
                .validFrom(LocalDate.of(2024, 02, 25))
                .build());
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
