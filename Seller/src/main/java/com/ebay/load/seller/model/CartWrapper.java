package com.ebay.load.seller.model;

import com.fasterxml.jackson.databind.util.Annotations;

import java.util.List;

public class CartWrapper {
    private List<Cart> cart;

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

}
