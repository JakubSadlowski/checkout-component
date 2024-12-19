package org.js.checkoutcomponent.errors;

public class CartItemNotFoundException extends ServiceGeneralException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
