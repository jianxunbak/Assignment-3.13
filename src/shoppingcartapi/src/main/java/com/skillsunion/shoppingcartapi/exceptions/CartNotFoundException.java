package com.skillsunion.shoppingcartapi.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Integer id) {
        super("Could not find cart with id: " + id + ".");
    }
}
