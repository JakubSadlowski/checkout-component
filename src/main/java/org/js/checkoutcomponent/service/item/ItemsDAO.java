package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ItemsDAO {
    Map<String, ItemEntity> getItems(Set<String> itemIds);

    Map<String, ItemDiscountEntity> getItemDiscounts(Set<String> itemIds);

    List<BundleDiscountEntity> getBundleDiscounts(Set<String> itemIds);
}
