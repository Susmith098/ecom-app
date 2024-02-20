package com.susmith.ecomapp.Service.serviceImpl;

import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Entity.Product;
import com.susmith.ecomapp.Entity.User;
import com.susmith.ecomapp.Entity.UserCart;
import com.susmith.ecomapp.Repository.OrderRepository;
import com.susmith.ecomapp.Repository.UserCartRepository;
import com.susmith.ecomapp.Service.CartService;
import com.susmith.ecomapp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserCartRepository userCartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    public UserCart getUserCart(User user) {
        return userCartRepository.findByUser(user);
    }

    public UserCart addToCart(User user, Long productId) {
        UserCart userCart = userCartRepository.findByUser(user);

        if (userCart == null) {
            userCart = new UserCart(user, new ArrayList<>());
        }

        Optional<Product> productOptional = productService.getProductById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            userCart.getProducts().add(product);
            userCart.getSelectedProductIds().add(productId);
        }

        userCart.setTotalCartAmount(userCart.getTotalCartAmount());

        userCartRepository.save(userCart);
        return userCart;
    }

    public UserCart removeFromCart(User user, Long productId) {
        UserCart userCart = userCartRepository.findByUser(user);
        if (userCart != null) {
            userCart.getProducts().removeIf(product -> product.getId().equals(productId));
            userCart.getSelectedProductIds().remove(productId);

            userCart.setTotalCartAmount(userCart.getTotalCartAmount());

            userCartRepository.save(userCart);
        }
        return userCart;
    }

    public double calculateTotalCartAmount(User user) {
        UserCart userCart = getUserCart(user);
        return userCart != null ? userCart.getTotalCartAmount() : 0.0;
    }



    @Override
    public void clearCart(User user) {
        UserCart userCart = getUserCart(user);
        if (userCart != null) {
            userCart.getProducts().clear();
            userCart.getSelectedProductIds().clear();
            userCart.setTotalCartAmount(0.0);
            userCartRepository.save(userCart);
        }
    }
}
