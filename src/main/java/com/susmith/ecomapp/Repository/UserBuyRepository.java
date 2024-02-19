package com.susmith.ecomapp.Repository;


import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBuyRepository extends JpaRepository<UserBuy, Long> {
    UserBuy findByUser(User user);
}
