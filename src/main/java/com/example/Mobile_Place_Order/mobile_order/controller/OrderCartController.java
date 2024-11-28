package com.example.Mobile_Place_Order.mobile_order.controller;

import com.example.Mobile_Place_Order.mobile_order.model.OrderCart;
import com.example.Mobile_Place_Order.mobile_order.model.OrderCartItem;
import com.example.Mobile_Place_Order.mobile_order.model.Product;
import com.example.Mobile_Place_Order.mobile_order.repository.OrderCartRepository;
import com.example.Mobile_Place_Order.mobile_order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class OrderCartController {

    @Autowired
    private OrderCartRepository orderCartRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get cart details
    @GetMapping("/{id}")
    public OrderCart getCart(@PathVariable Long id) {
        return orderCartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Add product to cart
    @PostMapping("/{cartId}/add")
    public OrderCart addProductToCart(@PathVariable Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
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
            cart.getItems().add(newItem);
        }

        return orderCartRepository.save(cart);
    }

    // Clear cart
    @DeleteMapping("/{id}/clear")
    public void clearCart(@PathVariable Long id) {
        OrderCart cart = orderCartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getItems().clear();
        orderCartRepository.save(cart);
    }
}
