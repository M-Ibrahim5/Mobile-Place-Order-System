package com.example.Mobile_Place_Order.mobile_order.controller;

import com.example.Mobile_Place_Order.mobile_order.model.OrderCart;
import com.example.Mobile_Place_Order.mobile_order.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class OrderCartController {

    @Autowired
    private OrderCartService orderCartService;

    // Get cart details
    @GetMapping("/{id}")
    public OrderCart getCart(@PathVariable Long id) {
        return orderCartService.getCart(id);
    }

    // Add product to cart
    @PostMapping("/{cartId}/add")
    public OrderCart addProductToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return orderCartService.addProductToCart(cartId, productId, quantity);
    }

    // Clear cart
    @DeleteMapping("/{id}/clear")
    public void clearCart(@PathVariable Long id) {
        orderCartService.clearCart(id);
    }
}
