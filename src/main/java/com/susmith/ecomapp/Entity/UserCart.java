package com.susmith.ecomapp.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
public class UserCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_cart_selected_products", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "product_id")
    private List<Long> selectedProductIds = new ArrayList<>();


    private double totalAmount;
//    public double getTotalCartAmount() {
//        double totalAmount = 0.0;
//        for (Product product : products) {
//            totalAmount += product.getPrice();
//        }
//        return totalAmount;
//    }

    public double getTotalCartAmount() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void setTotalCartAmount( double totalAmount){
        this.totalAmount = totalAmount;

    }

    public void clearCart() {
        products.clear();
        selectedProductIds.clear();
        totalAmount = 0.0;
    }

    public UserCart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public UserCart() {
    }

}
