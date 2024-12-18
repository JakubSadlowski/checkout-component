package org.js.checkoutcomponent.service.checkout.mapper;

import org.js.checkoutcomponent.service.checkout.data.BundleDiscount;
import org.js.checkoutcomponent.service.checkout.data.Item;
import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

public class ItemAndBundlesMapper {
    public static Item mapFromDao(ItemEntity itemDao, ItemDiscountEntity itemDiscountDao) {
        return Item.builder()
            .id(itemDao.getId())
            .normalPrice(itemDao.getNormalPrice())
            .specialPrice(itemDiscountDao.getSpecialPrice())
            .requiredQuantity(itemDiscountDao.getQuantity())
            .build();
    }

    public static BundleDiscount mapFromDao(BundleDiscountEntity bundleDao) {
        return BundleDiscount.builder()
            .firstItemID(bundleDao.getFromItemId())
            .secondItemID(bundleDao.getToItemId())
            .discount(bundleDao.getDiscountPrice())
            .build();
    }
}
