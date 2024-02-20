package com.susmith.ecomapp.Service;



import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    List<Order> getUserOrders();

    Order createOrderForCurrentUser();

    void cancelOrderAndAddToWallet(Long orderId);

    void updateOrderStatus(Long orderId, String status);

}
