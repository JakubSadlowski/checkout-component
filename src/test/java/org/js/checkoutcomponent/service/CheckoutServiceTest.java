package org.js.checkoutcomponent.service;

import org.js.checkoutcomponent.service.checkout.data.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckoutServiceTest {
    private CheckoutService checkoutService;
    private Item itemA, itemB, itemC, itemD;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();

        itemA = new Item();
        itemA.setId("A");
        itemA.setNormalPrice(40);
        itemA.setSpecialPrice(30);
        itemA.setRequiredQuantity(3);

        itemB = new Item();
        itemB.setId("B");
        itemB.setNormalPrice(10);
        itemB.setSpecialPrice(7.5);
        itemB.setRequiredQuantity(2);

        itemC = new Item();
        itemC.setId("C");
        itemC.setNormalPrice(30);
        itemC.setSpecialPrice(20);
        itemC.setRequiredQuantity(4);

        itemD = new Item();
        itemD.setId("D");
        itemD.setNormalPrice(25);
        itemD.setSpecialPrice(23.5);
        itemD.setRequiredQuantity(2);
    }

    @Test
    void calculateItemPriceTest() {
        // Given
        int quantity = 4;

        // When


        // Then
    }
}