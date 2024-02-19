package com.susmith.ecomapp.Controllers.customer;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserCart;
import com.susmith.ecomapp.Service.CartService;
import com.susmith.ecomapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<UserCart> getUserCart() {
        User user = userService.getCurrentUser();
        UserCart userCart = cartService.getUserCart(user);
        return ResponseEntity.ok(userCart);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<UserCart> addToCart(@PathVariable Long productId) {
        User user = userService.getCurrentUser();
        UserCart userCart = cartService.addToCart(user, productId);
        return ResponseEntity.ok(userCart);
    }

    @PostMapping("/remove/{productId}")
    public ResponseEntity<UserCart> removeFromCart(@PathVariable Long productId) {
        User user = userService.getCurrentUser();
        UserCart userCart = cartService.removeFromCart(user, productId);
        return ResponseEntity.ok(userCart);
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<Double> calculateTotalCartAmount() {
        User user = userService.getCurrentUser();
        double totalAmount = cartService.calculateTotalCartAmount(user);
        return ResponseEntity.ok(totalAmount);
    }
}
