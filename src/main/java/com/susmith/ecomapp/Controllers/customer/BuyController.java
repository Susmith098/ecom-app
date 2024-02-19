package com.susmith.ecomapp.Controllers.customer;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserBuy;
import com.susmith.ecomapp.Service.BuyService;
import com.susmith.ecomapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/buy")
public class BuyController {

    //Direct Buy controller

    @Autowired
    private BuyService userBuyService;

    @Autowired
    private UserService userService;

//    @GetMapping("/get")
//    public ResponseEntity<UserBuy> getUserBuy() {
//        User user = userService.getCurrentUser();
//        UserBuy userBuy = userBuyService.getUserBuy(user);
//        return ResponseEntity.ok(userBuy);
//    }

    @PostMapping("/{productId}")
    public ResponseEntity<UserBuy> buyNow(@PathVariable Long productId) {
        User user = userService.getCurrentUser();
        UserBuy userBuy = userBuyService.buyNow(user, productId);
        return ResponseEntity.ok(userBuy);
    }
}
