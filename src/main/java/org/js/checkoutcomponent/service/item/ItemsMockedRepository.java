package org.js.checkoutcomponent.service.item;

import org.js.checkoutcomponent.service.item.entities.BundleDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemDiscountEntity;
import org.js.checkoutcomponent.service.item.entities.ItemEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

class ItemsMockedRepository {
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
        .validFrom(LocalDate.of(2024, 01, 21))
        .build();

    public static final ItemDiscountEntity DISCOUNT_B = ItemDiscountEntity.builder()
        .id("2")
        .itemId("B")
        .specialPrice(new BigDecimal("7.5"))
        .quantity(3)
        .validFrom(LocalDate.of(2024, 05, 23))
        .build();

    public static final ItemDiscountEntity DISCOUNT_C = ItemDiscountEntity.builder()
        .id("3")
        .itemId("C")
        .specialPrice(new BigDecimal("20.0"))
        .quantity(3)
        .validFrom(LocalDate.of(2024, 01, 10))
        .build();

    public static final ItemDiscountEntity DISCOUNT_D = ItemDiscountEntity.builder()
        .id("4")
        .itemId("D")
        .specialPrice(new BigDecimal("23.5"))
        .quantity(3)
        .validFrom(LocalDate.of(2024, 02, 25))
        .build();

    // Bundle discounts
    public static final BundleDiscountEntity BUNDLE_A_B = BundleDiscountEntity.builder()
        .id("1")
        .fromItemId("A")
        .toItemId("B")
        .discountPrice(new BigDecimal("20.0"))
        .validFrom(LocalDate.of(2024, 02, 15))
        .build();

    public static final BundleDiscountEntity BUNDLE_C_D = BundleDiscountEntity.builder()
        .id("2")
        .fromItemId("C")
        .toItemId("D")
        .discountPrice(new BigDecimal("10.0"))
        .validFrom(LocalDate.of(2024, 01, 11))
        .build();
}