package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class ItemsMock {

    // Items
    public static final ItemEntity ITEM_A = ItemEntity.builder()
        .id("A")
        .name("Perfumes")
        .normalPrice(new BigDecimal("40.0"))
        .build();

    public static final ItemEntity ITEM_B = ItemEntity.builder()
        .id("B")
        .name("Soap")
        .normalPrice(new BigDecimal("10.0"))
        .build();

    public static final ItemEntity ITEM_C = ItemEntity.builder()
        .id("C")
        .name("Shampoo")
        .normalPrice(new BigDecimal("30.0"))
        .build();

    public static final ItemEntity ITEM_D = ItemEntity.builder()
        .id("D")
        .name("Towel")
        .normalPrice(new BigDecimal("25.0"))
        .build();

    public static final ItemEntity ITEM_E = ItemEntity.builder()
        .id("E")
        .name("Creme")
        .normalPrice(new BigDecimal("13.0"))
        .build();

    // Item discounts
    public static final ItemDiscountEntity DISCOUNT_A = ItemDiscountEntity.builder()
        .id("1")
        .itemId("A")
        .specialPrice(new BigDecimal("30.0"))
        .quantity(3)
        .validFrom(LocalDate.of(2024, 1, 21))
        .build();

    public static final ItemDiscountEntity DISCOUNT_B = ItemDiscountEntity.builder()
        .id("2")
        .itemId("B")
        .specialPrice(new BigDecimal("7.5"))
        .quantity(2)
        .validFrom(LocalDate.of(2024, 5, 23))
        .build();

    public static final ItemDiscountEntity DISCOUNT_C = ItemDiscountEntity.builder()
        .id("3")
        .itemId("C")
        .specialPrice(new BigDecimal("20.0"))
        .quantity(4)
        .validFrom(LocalDate.of(2024, 1, 10))
        .build();

    public static final ItemDiscountEntity DISCOUNT_D = ItemDiscountEntity.builder()
        .id("4")
        .itemId("D")
        .specialPrice(new BigDecimal("23.5"))
        .quantity(2)
        .validFrom(LocalDate.of(2024, 2, 25))
        .build();

    // Bundle discounts
    public static final BundleDiscountEntity BUNDLE_A_B = BundleDiscountEntity.builder()
        .id("1")
        .fromItemId("A")
        .toItemId("B")
        .discountPrice(new BigDecimal("20.0"))
        .validFrom(LocalDate.of(2024, 2, 15))
        .build();

    public static final BundleDiscountEntity BUNDLE_C_D = BundleDiscountEntity.builder()
        .id("2")
        .fromItemId("C")
        .toItemId("D")
        .discountPrice(new BigDecimal("10.0"))
        .validFrom(LocalDate.of(2024, 1, 11))
        .build();

    public static final Map<String, ItemEntity> itemsMap = Map.of("A", ITEM_A, "B", ITEM_B, "C", ITEM_C, "D", ITEM_D, "E", ITEM_E);

    public static final Map<String, ItemDiscountEntity> discountsMap = Map.of("A", DISCOUNT_A, "B", DISCOUNT_B, "C", DISCOUNT_C, "D", DISCOUNT_D);

    public static final Map<String, BundleDiscountEntity> bundleDiscountsMap = Map.of(BUNDLE_A_B.getId(), BUNDLE_A_B, BUNDLE_C_D.getId(), BUNDLE_C_D);

}
