package org.js.checkoutcomponent.model;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutRequest {
    private List<CartItem> items;
}
