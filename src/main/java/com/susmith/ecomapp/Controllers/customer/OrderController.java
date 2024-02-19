package com.susmith.ecomapp.Controllers.customer;

import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity<List<Order>> getUserOrders() {
        List<Order> userOrders = orderService.getUserOrders();
        return ResponseEntity.ok(userOrders);
    }
}
