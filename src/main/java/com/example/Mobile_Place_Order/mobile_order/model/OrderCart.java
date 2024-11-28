package com.example.Mobile_Place_Order.mobile_order.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderCartItem> items = new ArrayList<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderCartItem> getItems() {
        return items;
    }

    public void setItems(List<OrderCartItem> items) {
        this.items = items;
    }
}
