package com.susmith.ecomapp.Controllers.customer;

import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/orders")
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder() {
        Order createdOrder = orderService.createOrderForCurrentUser();
        return new ResponseEntity<>("Order created with ID: " + createdOrder.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders() {
        List<Order> userOrders = orderService.getUserOrders();
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrderAndAddToWallet(orderId);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }
}
