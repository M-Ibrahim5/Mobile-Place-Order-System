package com.example.Mobile_Place_Order.mobile_order.repository;

import com.example.Mobile_Place_Order.mobile_order.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
