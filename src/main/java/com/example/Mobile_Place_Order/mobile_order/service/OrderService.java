package com.example.Mobile_Place_Order.mobile_order.service;

import com.example.Mobile_Place_Order.mobile_order.model.*;
import com.example.Mobile_Place_Order.mobile_order.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public Order placeOrder(Long cartId) {
        // Find the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found for ID: " + cartId));

        // Check product availability and update stock
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            // Deduct the stock from the product
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        }

        // Create the order
        Order order = new Order();
        order.setCustomerName(cart.getCustomerName());
        order.setCustomerAddress(cart.getCustomerAddress());
        order.setOrderDate(new Date());
        order.setItems(cart.getItems().stream()
                .map(cartItem -> new OrderItem(null, cartItem.getProduct(), cartItem.getQuantity()))
                .collect(Collectors.toList()));

        // Calculate the total
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getLinePrice())
                .sum();
        order.setTotal(total);

        // Clear the cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    public List<Order> getOrderHistory(String customerName) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }
}
