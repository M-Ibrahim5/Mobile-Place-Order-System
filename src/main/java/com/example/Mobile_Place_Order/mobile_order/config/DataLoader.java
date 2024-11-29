package com.example.Mobile_Place_Order.mobile_order.config;

import com.example.Mobile_Place_Order.mobile_order.model.Cart;
import com.example.Mobile_Place_Order.mobile_order.model.Product;
import com.example.Mobile_Place_Order.mobile_order.repository.CartRepository;
import com.example.Mobile_Place_Order.mobile_order.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository, CartRepository cartRepository) {
        return args -> {
            // Add initial products
            productRepository.save(new Product(null, "Iphone 16 Pro Max", "smartphone", 10000.00, 18));
            productRepository.save(new Product(null, "Iphone 14 Pro", "smartphone", 7000.00, 14));
            productRepository.save(new Product(null, "Mac Book Pro Ultra Max", "laptop", 12000.00, 12));

            // Add initial cart
            Cart cart = new Cart();
            cart.setCustomerName("John Doe"); // Example customer name
            cart.setCustomerAddress("NO. 45 Taman Bahagia, Melaka, Malaysia"); // New address
            cart.setItems(new ArrayList<>()); // Initially empty cart
            cartRepository.save(cart);
        };
    }
}
