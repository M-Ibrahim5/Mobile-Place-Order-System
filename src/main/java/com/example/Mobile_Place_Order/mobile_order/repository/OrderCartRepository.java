package com.example.Mobile_Place_Order.mobile_order.repository;

import com.example.Mobile_Place_Order.mobile_order.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
}
