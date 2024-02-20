package com.susmith.ecomapp.Service;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserCart;

public interface CartService {

    UserCart getUserCart(User user);

    UserCart addToCart(User user, Long productId);

    UserCart removeFromCart(User user, Long productId);

    double calculateTotalCartAmount(User user);
    void clearCart(User user);
}
