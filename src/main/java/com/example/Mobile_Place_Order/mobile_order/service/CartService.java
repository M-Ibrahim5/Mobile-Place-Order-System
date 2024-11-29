package com.example.Mobile_Place_Order.mobile_order.service;

import com.example.Mobile_Place_Order.mobile_order.model.Cart;
import com.example.Mobile_Place_Order.mobile_order.model.CartItem;
import com.example.Mobile_Place_Order.mobile_order.model.Product;
import com.example.Mobile_Place_Order.mobile_order.repository.CartRepository;
import com.example.Mobile_Place_Order.mobile_order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        // Find the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found for ID: " + cartId));

        // Find the product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found for ID: " + productId));

        // Check product availability
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName() +
                    ". Available quantity: " + product.getQuantity());
        }

        // Add or update the cart item
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity); // Update quantity
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity); // Set quantity
            cart.getItems().add(cartItem);
        }

        // Calculate total for the cart
        double total = cart.getItems().stream()
                .mapToDouble(item -> item.getLinePrice()) // Sum the line prices
                .sum();
        cart.setTotal(total);

        return cartRepository.save(cart);
    }



    public Cart getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found for ID: " + cartId));

        // Calculate line price for each item
        for (CartItem item : cart.getItems()) {
            item.setLinePrice(item.getProduct().getPrice() * item.getQuantity());
        }

        // Calculate total price
        double total = cart.getItems().stream()
                .mapToDouble(CartItem::getLinePrice)
                .sum();
        cart.setTotal(total);

        return cart;
    }



}
