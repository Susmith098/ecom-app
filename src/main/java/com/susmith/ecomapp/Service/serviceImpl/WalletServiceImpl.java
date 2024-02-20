package com.susmith.ecomapp.Service.serviceImpl;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.Wallet;
import com.susmith.ecomapp.Repository.WalletRepository;
import com.susmith.ecomapp.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void addToWallet(User user, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUser(user);

        if (wallet == null) {
            wallet = new Wallet();
            wallet.setUser(user);
            wallet.setBalance(amount);
        } else {
            BigDecimal newBalance = wallet.getBalance().add(amount);
            wallet.setBalance(newBalance);
        }

        walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletByUser(User user) {
        return walletRepository.findByUser(user);
    }
}
