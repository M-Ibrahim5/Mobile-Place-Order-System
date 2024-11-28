package com.example.Mobile_Place_Order.mobile_order.repository;

import com.example.Mobile_Place_Order.mobile_order.model.OrderCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCartItemRepository extends JpaRepository<OrderCartItem, Long> {
}
