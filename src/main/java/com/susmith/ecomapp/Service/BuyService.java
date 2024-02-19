package com.susmith.ecomapp.Service;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserBuy;

public interface BuyService {

    UserBuy getUserBuy(User user);

    UserBuy buyNow(User user, Long productId);


}
