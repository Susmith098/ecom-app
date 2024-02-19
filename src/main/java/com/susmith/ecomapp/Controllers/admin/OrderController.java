package com.susmith.ecomapp.Controllers.admin;


import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Order Management

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
//        orderService.deleteOrder(id);
//        return new ResponseEntity<>("Order successfully deleted", HttpStatus.NO_CONTENT);
//    }

//    @PostMapping("/orders")
//    public Order createOrder(@RequestBody Order order) {
//        return orderService.createOrder(order);
//    }
//
//    @PutMapping("/orders/{id}")
//    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
//        return orderService.updateOrder(id, order);
//    }



}
