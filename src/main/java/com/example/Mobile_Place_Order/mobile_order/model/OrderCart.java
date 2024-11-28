package com.example.Mobile_Place_Order.mobile_order.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_cart")
public class OrderCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerAddress;

    @OneToMany(mappedBy = "orderCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderCartItem> items = new ArrayList<>();

    @Version
    private Integer version;
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<OrderCartItem> getItems() {
        return items;
    }

    public void setItems(List<OrderCartItem> items) {
        this.items = items;
    }
}
