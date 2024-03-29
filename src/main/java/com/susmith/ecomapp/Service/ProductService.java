package com.susmith.ecomapp.Service;



import com.susmith.ecomapp.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> getProductsByCategoryId(int categoryId);

    List<Product> searchProducts(String keyword);
}
