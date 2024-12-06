package com.skillsunion.shoppingcartapi.exceptions;

public class CatalogueNotFoundException extends RuntimeException {
    public CatalogueNotFoundException(Integer id) {
        super("Could not find Catalogue with id: " + id + ".");
    }
}
