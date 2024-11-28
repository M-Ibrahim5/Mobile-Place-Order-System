package com.example.Mobile_Place_Order.mobile_order.repository;

import com.example.Mobile_Place_Order.mobile_order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
