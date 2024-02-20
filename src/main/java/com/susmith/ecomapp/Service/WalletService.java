package com.susmith.ecomapp.Service;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.Wallet;

import java.math.BigDecimal;

public interface WalletService {

    void addToWallet(User user, BigDecimal amount);

    Wallet getWalletByUser(User user);
}
