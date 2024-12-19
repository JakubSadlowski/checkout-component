package org.js.checkoutcomponent.service.item;

import lombok.extern.apachecommons.CommonsLog;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;
import org.js.checkoutcomponent.service.item.mapper.ItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CommonsLog
public class ItemsDAOImpl implements ItemsDAO {
    private final ItemsMapper itemsMapper;

    @Autowired
    public ItemsDAOImpl(ItemsMapper itemsMapper) {
        this.itemsMapper = itemsMapper;
    }

    @Override
    public Map<String, ItemEntity> getItems(Set<String> itemIds) {
        Map<String, ItemEntity> items = getAllItems().stream()
            .filter(e -> itemIds.contains(e.getId()))
            .collect(Collectors.toMap(ItemEntity::getId, item -> item));

        log.debug("Items: " + Arrays.toString(items.values()
            .toArray()));

        return items;
    }

    @Override
    public Map<String, ItemDiscountEntity> getItemDiscounts(Set<String> itemIds) {
        Map<String, ItemDiscountEntity> discounts = getAllItemDiscounts().stream()
            .filter(e -> itemIds.contains(e.getItemId()))
            .collect(Collectors.toMap(ItemDiscountEntity::getItemId, discount -> discount));

        log.debug("Discounts: " + Arrays.toString(discounts.values()
            .toArray()));

        return discounts;
    }

    @Override
    public Map<String, BundleDiscountEntity> getBundleDiscounts(Set<String> itemIds) {
        Map<String, BundleDiscountEntity> bundleDiscounts = getAllBundleDiscounts().stream()
            .filter(e -> itemIds.contains(e.getFromItemId()) && itemIds.contains(e.getToItemId()))
            .collect(Collectors.toMap(BundleDiscountEntity::getId, bundle -> bundle));

        log.debug("Bundle Discounts: " + Arrays.toString(bundleDiscounts.values()
            .toArray()));

        return bundleDiscounts;
    }

    private List<ItemEntity> getAllItems() {
        return itemsMapper.fetchAllItems();
    }

    private List<ItemDiscountEntity> getAllItemDiscounts() {
        return itemsMapper.fetchAllDiscounts();
    }

    private List<BundleDiscountEntity> getAllBundleDiscounts() {
        return itemsMapper.fetchAllBundleDiscounts();
    }
}
