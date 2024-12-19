package org.js.checkoutcomponent.errors;

public class CartItemWithZeroOrLessQuantity extends ServiceGeneralException {
    public CartItemWithZeroOrLessQuantity(String message) {
        super(message);
    }
}
