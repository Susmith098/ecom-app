package com.susmith.ecomapp.Service.serviceImpl;


import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Repository.OrderRepository;
import com.susmith.ecomapp.Service.OrderService;
import com.susmith.ecomapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

//    @Override
//    public Order createOrder(Order order) {
//        return orderRepository.save(order);
//    }
//
//    @Override
//    public Order updateOrder(Long id, Order order) {
//        return orderRepository.save(order);
//    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getUserOrders() {
        User user = userService.getCurrentUser();
        return orderRepository.findByUser(user);
    }
}
