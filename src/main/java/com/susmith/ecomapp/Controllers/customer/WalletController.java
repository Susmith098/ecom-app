package com.susmith.ecomapp.Controllers.customer;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.Wallet;
import com.susmith.ecomapp.Service.UserService;
import com.susmith.ecomapp.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user/wallet")
public class WalletController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    //Wallet balance

    @GetMapping("/balance")
    public ResponseEntity<String> getWalletBalance(@AuthenticationPrincipal User authenticatedUser) {
        User user = userService.getUserById(authenticatedUser.getId());
        Wallet wallet = walletService.getWalletByUser(user);

        if (wallet != null) {
            BigDecimal balance = wallet.getBalance();
            return new ResponseEntity<>("Wallet balance: " + balance.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Wallet not found", HttpStatus.NOT_FOUND);
        }
    }
}
