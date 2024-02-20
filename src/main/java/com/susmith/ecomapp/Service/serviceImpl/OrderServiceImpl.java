package com.susmith.ecomapp.Service.serviceImpl;


import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Entity.OrderStatus;
import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserCart;
import com.susmith.ecomapp.Entity.Product;
import com.susmith.ecomapp.Entity.OrderItem;
import com.susmith.ecomapp.Repository.OrderRepository;
import com.susmith.ecomapp.Repository.ProductRepository;
import com.susmith.ecomapp.Repository.UserCartRepository;
import com.susmith.ecomapp.Service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private WalletService walletService;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order createOrderForCurrentUser() {
        User user = userService.getCurrentUser();
        UserCart userCart = userCartRepository.findByUser(user);

        double totalAmount = userCart.getTotalCartAmount();

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(BigDecimal.valueOf(totalAmount));
        order.setStatus(OrderStatus.PAID);
        order.setOrderDateTime(LocalDateTime.now());

        // Save the order
        orderRepository.save(order);

        // Add order items to the order
        List<Long> productIds = userCart.getSelectedProductIds();
        for (Long productId : productIds) {
            Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(1);

            order.getOrderItems().add(orderItem);
        }

        // Save the order again to update order items
        orderRepository.save(order);

        //Clear cart items
        userCart.clearCart();

        return order;
    }

    @Override
    public void cancelOrderAndAddToWallet(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            if (order.getUser().equals(userService.getCurrentUser())) {
                walletService.addToWallet(order.getUser(), order.getTotalAmount());
                order.setStatus(OrderStatus.CANCELED);
                orderRepository.save(order);
            }

        }
    }

    public List<Order> getUserOrders() {
        User user = userService.getCurrentUser();
        return orderRepository.findByUser(user);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(OrderStatus.valueOf(status));
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
    }

}

