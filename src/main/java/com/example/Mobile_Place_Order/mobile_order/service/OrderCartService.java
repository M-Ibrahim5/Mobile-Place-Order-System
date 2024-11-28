package com.example.Mobile_Place_Order.mobile_order.service;

import com.example.Mobile_Place_Order.mobile_order.model.OrderCart;
import com.example.Mobile_Place_Order.mobile_order.model.OrderCartItem;
import com.example.Mobile_Place_Order.mobile_order.model.Product;
import com.example.Mobile_Place_Order.mobile_order.repository.OrderCartRepository;
import com.example.Mobile_Place_Order.mobile_order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCartService {

    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private ProductRepository productRepository;

    // Retrieve a cart by ID
    public OrderCart getCart(Long id) {
        return orderCartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Add a product to the cart
    public OrderCart addProductToCart(Long cartId, Long productId, int quantity) {
        OrderCart cart = orderCartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if product already exists in the cart
        Optional<OrderCartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            OrderCartItem newItem = new OrderCartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setOrderCart(cart); // Maintain bidirectional relationship
            cart.getItems().add(newItem);
        }

        return orderCartRepository.save(cart);
    }

    // Clear all items from the cart
    public void clearCart(Long id) {
        OrderCart cart = orderCartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        orderCartRepository.save(cart);
    }
}
