package com.susmith.ecomapp.Repository;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    UserCart findByUser(User user);
}
