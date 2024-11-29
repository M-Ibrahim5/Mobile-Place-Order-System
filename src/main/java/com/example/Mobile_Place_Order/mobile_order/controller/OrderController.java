package com.example.Mobile_Place_Order.mobile_order.controller;

import com.example.Mobile_Place_Order.mobile_order.model.Order;
import com.example.Mobile_Place_Order.mobile_order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{cartId}")
    public ResponseEntity<?> placeOrder(@PathVariable Long cartId) {
        try {
            Order order = orderService.placeOrder(cartId);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public List<Order> getOrderHistory(@RequestParam String customerName) {
        return orderService.getOrderHistory(customerName);
    }
}
