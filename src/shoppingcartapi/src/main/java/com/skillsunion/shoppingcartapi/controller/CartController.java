package com.skillsunion.shoppingcartapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.skillsunion.shoppingcartapi.entity.Cart;
import com.skillsunion.shoppingcartapi.entity.Catalogue;
import com.skillsunion.shoppingcartapi.exceptions.CartNotFoundException;
import com.skillsunion.shoppingcartapi.exceptions.CatalogueNotFoundException;
import com.skillsunion.shoppingcartapi.repository.CartRepository;
import com.skillsunion.shoppingcartapi.repository.CatalogueRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

/*
    Assignment:
    - Apply @RestController to the class.
    - Apply @GetMapping and @PostMapping to the methods.
    - Apply @PathVariable to the method parameter of get(int).

    Test:
    - Use a client HTTP Tool like YARC to consume the API you have just created.
    E.g. https://chrome.google.com/webstore/detail/yet-another-rest-client/ehafadccdcdedbhcbddihehiodgcddpl?hl=en
*/

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CatalogueRepository catalogueRepository;

    @Autowired
    private CartRepository cartRepository;

    // Get all records in the `cart` table
    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCart() {
        List<Cart> cart = cartRepository.findAll();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Get a record from `cart` table by given ID
    @GetMapping("/{itemId}")
    public ResponseEntity<Cart> getOneCartItem(@PathVariable Integer itemId) {
        Cart cartItem = cartRepository.findById(itemId).orElseThrow(() -> new CartNotFoundException(itemId));
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    // Create a record in `cart` table with the data in the request body. The given
    // `itemId` must be an existing record in the `catalogue` table.
    @PostMapping("")
    public ResponseEntity<Cart> addCatalogueToCart(@RequestParam Integer itemId, @RequestParam Integer quantity) {
        // Find the item in catague by itemId
        Catalogue catalogueItem = catalogueRepository.findById(itemId)
                .orElseThrow(() -> new CatalogueNotFoundException(itemId));
        // If item exist, set items into cart and set quantity
        Cart cart = new Cart();
        cart.setItem(catalogueItem);
        cart.setQuantity(quantity);
        cart.setCreatedAt(java.sql.Date.valueOf(LocalDate.now()));
        cart = cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
}
