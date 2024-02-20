package com.susmith.ecomapp.Service;

import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserBuy;

public interface BuyService {

    UserBuy buyNow(User user, Long productId);


}
