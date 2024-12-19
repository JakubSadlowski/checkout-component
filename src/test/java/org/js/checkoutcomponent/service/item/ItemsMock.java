package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.util.Map;

public class ItemsMock {

    public static final ItemEntity ITEM_A = ItemsMockedRepository.ITEM_A;

    public static final ItemEntity ITEM_B = ItemsMockedRepository.ITEM_B;

    public static final ItemEntity ITEM_C = ItemsMockedRepository.ITEM_C;

    public static final ItemEntity ITEM_D = ItemsMockedRepository.ITEM_D;

    public static final ItemEntity ITEM_E = ItemsMockedRepository.ITEM_E;

    public static final ItemDiscountEntity DISCOUNT_A = ItemsMockedRepository.DISCOUNT_A;

    public static final ItemDiscountEntity DISCOUNT_B = ItemsMockedRepository.DISCOUNT_B;

    public static final ItemDiscountEntity DISCOUNT_C = ItemsMockedRepository.DISCOUNT_C;

    public static final ItemDiscountEntity DISCOUNT_D = ItemsMockedRepository.DISCOUNT_D;

    public static final BundleDiscountEntity BUNDLE_A_B = ItemsMockedRepository.BUNDLE_A_B;

    public static final BundleDiscountEntity BUNDLE_C_D = ItemsMockedRepository.BUNDLE_C_D;

    public static final Map<String, ItemEntity> itemsMap = Map.of("A", ITEM_A, "B", ITEM_B, "C", ITEM_C, "D", ITEM_D, "E", ITEM_E);

    public static final Map<String, ItemDiscountEntity> discountsMap = Map.of("A", DISCOUNT_A, "B", DISCOUNT_B, "C", DISCOUNT_C, "D", DISCOUNT_D);

    public static final Map<String, BundleDiscountEntity> bundleDiscountsMap = Map.of(BUNDLE_A_B.getId(), BUNDLE_A_B, BUNDLE_C_D.getId(), BUNDLE_C_D);

}
