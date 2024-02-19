package com.susmith.ecomapp.Service;



import com.susmith.ecomapp.Entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

//    Order createOrder(Order order);
//
//    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id);

    List<Order> getUserOrders();
}
