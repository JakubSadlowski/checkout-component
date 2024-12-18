package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

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
    public Map<String, ItemDiscountEntity> getItemDiscounts(Set<String> itemIds) {
        return getAllItemDiscounts().stream()
            .filter(e -> itemIds.contains(e.getItemId()))
            .collect(Collectors.toMap(ItemDiscountEntity::getItemId, discount -> discount));
    }

    @Override
    public Map<String, BundleDiscountEntity> getBundleDiscounts(Set<String> itemIds) {
        return getAllBundleDiscounts().stream()
            .filter(e -> itemIds.contains(e.getFromItemId()) && itemIds.contains(e.getToItemId()))
            .collect(Collectors.toMap(BundleDiscountEntity::getId, bundle -> bundle));
    }

    private List<ItemEntity> getAllItems() {
        return List.of(ItemsMockedRepository.ITEM_A, ItemsMockedRepository.ITEM_B, ItemsMockedRepository.ITEM_C, ItemsMockedRepository.ITEM_D, ItemsMockedRepository.ITEM_E);
    }

    private List<ItemDiscountEntity> getAllItemDiscounts() {
        return List.of(ItemsMockedRepository.DISCOUNT_A, ItemsMockedRepository.DISCOUNT_B, ItemsMockedRepository.DISCOUNT_C, ItemsMockedRepository.DISCOUNT_D);
    }

    private List<BundleDiscountEntity> getAllBundleDiscounts() {
        return List.of(ItemsMockedRepository.BUNDLE_A_B, ItemsMockedRepository.BUNDLE_C_D);
    }
}
