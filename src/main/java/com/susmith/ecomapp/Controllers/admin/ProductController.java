package com.susmith.ecomapp.Controllers.admin;


import com.susmith.ecomapp.Entity.Category;
import com.susmith.ecomapp.Entity.Product;
import com.susmith.ecomapp.Entity.ProductImage;
import com.susmith.ecomapp.Service.CategoryService;
import com.susmith.ecomapp.Service.ProductImageService;
import com.susmith.ecomapp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    // Product Management

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<String> createProductWithImage(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("stockQty") int stockQty,
            @RequestParam("weight") double weight,
            @RequestParam("description") String description,
            @RequestParam("category") Long categoryId,
            @RequestParam("image") MultipartFile image) {

        try {
            // Validate inputs
            if (image == null || image.isEmpty()) {
                return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);
            }

            // Create Product
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setStockQty(stockQty);
            product.setWeight(weight);
            product.setDescription(description);

            // Retrieve Category
            Category category = categoryService.getCategoryById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            product.setCategory(category);

            // Create Product Image
            ProductImage productImage = new ProductImage();
            productImage.setImageName(image.getOriginalFilename());
            productImage.setProduct(product);

            product.setImages(Collections.singletonList(productImage));

            // Create Product
            Product createdProduct = productService.createProduct(product);

            // Save Product Image
            productImageService.saveImage(image, createdProduct.getId());

            return new ResponseEntity<>("Product and Image successfully created with ID: " + createdProduct.getId(), HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return new ResponseEntity<>("Failed to create product and image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>("Product successfully updated with ID: " + updatedProduct.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product successfully deleted", HttpStatus.NO_CONTENT);
    }
}
