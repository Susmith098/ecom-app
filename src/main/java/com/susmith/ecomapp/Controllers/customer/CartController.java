package com.susmith.ecomapp.Controllers.customer;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserCart;
import com.susmith.ecomapp.Service.CartService;
import com.susmith.ecomapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current Logged-in User: " + currentPrincipalName);

        User user = userService.getCurrentUser();
        System.out.println("Current Logged-in User ID: " + user.getId());

//        User user = userService.getCurrentUser();
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
