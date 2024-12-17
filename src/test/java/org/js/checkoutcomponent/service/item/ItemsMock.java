package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
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

    public static final Map<String, ItemEntity> itemsMap = new HashMap<String, ItemEntity>();

    static {
        itemsMap.put("A", ITEM_A);
        itemsMap.put("B", ITEM_B);
        itemsMap.put("C", ITEM_C);
        itemsMap.put("D", ITEM_D);
        itemsMap.put("E", ITEM_E);
    }
}
