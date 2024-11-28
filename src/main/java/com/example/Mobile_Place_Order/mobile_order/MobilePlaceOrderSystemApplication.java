package com.example.Mobile_Place_Order.mobile_order;


import com.example.Mobile_Place_Order.mobile_order.model.OrderCart;
import com.example.Mobile_Place_Order.mobile_order.repository.OrderCartRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class MobilePlaceOrderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilePlaceOrderSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(OrderCartRepository cartRepository) {
		return args -> {
			if (cartRepository.findById(1L).isEmpty()) {
				OrderCart defaultCart = new OrderCart();
				defaultCart.setCustomerName("Johnson");
				defaultCart.setCustomerAddress("No.34 Taman Indera, Melaka, Malaysia");

				// Add empty items list explicitly (not strictly required but for clarity)
				defaultCart.setItems(new ArrayList<>());

				// Save the default cart
				cartRepository.save(defaultCart);
				System.out.println("Default cart created with ID: 1");
			}
		};
	}
}
