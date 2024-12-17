package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.checkout.data.Item;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.util.List;

public interface ItemsDAO {
    List<ItemEntity> getItems(List<String> itemIds);
    List<ItemDiscountEntity> getItemDiscounts(String itemId);
    List<BundleDiscountEntity> getBundleDiscounts(String bundleId);
}
