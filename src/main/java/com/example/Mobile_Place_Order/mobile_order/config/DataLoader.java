package com.example.Mobile_Place_Order.mobile_order.config;

import com.example.Mobile_Place_Order.mobile_order.model.Product;
import com.example.Mobile_Place_Order.mobile_order.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(null, "Iphone 16 Pro Max", "smartphone", 10000.00, 8));
            productRepository.save(new Product(null, "Iphone 14 Pro", "smartphone", 70000.00, 4));
            productRepository.save(new Product(null, "Mac Book Pro Ultra Max", "laptop", 10000.00, 2));
        };
    }
}
