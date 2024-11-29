package com.example.Mobile_Place_Order.mobile_order.repository;

import com.example.Mobile_Place_Order.mobile_order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
