package com.skillsunion.shoppingcartapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillsunion.shoppingcartapi.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
