package com.skillsunion.shoppingcartapi.controller;

/*
    Assignment:
    - Apply @RestController to the class.
    - Apply @GetMapping and @PostMapping to the methods.
    - Apply @PathVariable to the method parameter of get(int).

    Test:
    - Use a client HTTP Tool like YARC to consume the API you have just created.
    E.g. https://chrome.google.com/webstore/detail/yet-another-rest-client/ehafadccdcdedbhcbddihehiodgcddpl?hl=en
*/

public class CartController {
    
    public String list(){
        return "GET /carts ok";
    }

    public String get(int id){
        return "GET /carts/"+id+" ok";
    }

    public String create(){
        return "POST /carts ok";
    }
}
