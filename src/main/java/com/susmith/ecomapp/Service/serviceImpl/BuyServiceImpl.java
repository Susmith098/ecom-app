package com.susmith.ecomapp.Service.serviceImpl;

import com.susmith.ecomapp.Entity.Product;
import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserBuy;
import com.susmith.ecomapp.Repository.UserBuyRepository;
import com.susmith.ecomapp.Service.BuyService;
import com.susmith.ecomapp.Service.ProductService;
import com.susmith.ecomapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    private UserBuyRepository userBuyRepository;

    @Autowired
    private ProductService productService;

    public UserBuy getUserBuy(User user) {
        return userBuyRepository.findByUser(user);
    }

    public UserBuy buyNow(User user, Long productId) {
        UserBuy userBuy = userBuyRepository.findByUser(user);
        if (userBuy == null) {
            userBuy = new UserBuy();
            userBuy.setUser(user);
        }

        Optional<Product> productOptional = productService.getProductById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            userBuy.getProducts().add(product);
        } else {
            throw new ProductNotFoundException("Product not found for ID: " + productId);
        }

        userBuyRepository.save(userBuy);
        return userBuy;
    }
}
