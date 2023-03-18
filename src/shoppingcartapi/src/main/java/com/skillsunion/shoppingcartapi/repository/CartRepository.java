package com.skillsunion.shoppingcartapi.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.skillsunion.shoppingcartapi.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository <Cart, Integer>{

	Cart findById(@Param("Id") int id);

}
