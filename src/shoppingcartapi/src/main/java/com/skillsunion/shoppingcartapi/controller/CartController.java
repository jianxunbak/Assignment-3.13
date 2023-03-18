package com.skillsunion.shoppingcartapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skillsunion.shoppingcartapi.entity.Cart;
import com.skillsunion.shoppingcartapi.entity.Catalogue;
import com.skillsunion.shoppingcartapi.repository.CartRepository;
import com.skillsunion.shoppingcartapi.repository.CatalogueRepository;

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
    CartRepository cartRepo;

    @Autowired
    CatalogueRepository catalogueRepo;


    @GetMapping
    public ResponseEntity<List<Cart>> list(){
        List<Cart> cartList = cartRepo.findAll();
        if(cartList.isEmpty()) return new ResponseEntity<List<Cart>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Cart> get(@PathVariable int id){
        Cart cart = cartRepo.findById(id);
        if(cart == null) return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Cart>(cart,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CartRequest cart){
        Cart newCart = new Cart();
        Optional<Catalogue> item = catalogueRepo.findById(cart.getItemId());

        if(item.isPresent()){


            newCart.setItem(item.get());
            newCart.setQuantity(cart.getQuantity());
            cartRepo.save(newCart);

            URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newCart.getId()).toUri();
            return ResponseEntity.created(location).build();
        }

        return ResponseEntity.badRequest().build();
    }
}

class CartRequest{
    private int itemId;
    private int quantity;
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}