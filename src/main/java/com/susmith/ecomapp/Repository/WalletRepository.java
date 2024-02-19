package com.susmith.ecomapp.Repository;


import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
}
