package com.skillsunion.shoppingcartapi.controller;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogueController {
    
    @RequestMapping(value="/catalogues", method = RequestMethod.GET ,consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String list(@RequestParam String search){
        return "GET /catalogues with search="+search+" returned ok";
    }

    /*
        Switch between @PathVariable and @RequestParam to help learners understand
        the difference.

        @PathVariable => /catalogues/1
        @RequestParam => /catalogues?id=1
    */
    @GetMapping(value = "/catalogues/{id}")
    public String get(@PathVariable int id){
        return "GET /catalogues/"+id+" returned ok";
    }

    /*
        By default, a @PostMapping assumes application/json content type.
    */
    @PostMapping(value = "/catalogues")
    public @ResponseBody RequestBodyTempData create(@RequestBody RequestBodyTempData data){
        return data;
    }
}

/*
    This is a private class, not accessible outside this java file.
    We will use this for now. In the future, the request body
*/
class RequestBodyTempData {
    String name;
    float price;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}