package com.susmith.ecomapp.Service.serviceImpl;

import com.susmith.ecomapp.Entity.Product;
import com.susmith.ecomapp.Entity.ProductImage;
import com.susmith.ecomapp.Repository.ProductImageRepository;
import com.susmith.ecomapp.Service.ProductImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {

    private final String UPLOAD_DIR = "/src/main/resources/static/ProductImages";

    @Autowired
    private ProductImageRepository productImageRepository;

    public void saveImage(MultipartFile image, Long productId) throws IOException {
        // Create upload directory if not exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save image to the directory
        String fileName = productId + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.write(filePath, image.getBytes());

        // Save image details to the database
        ProductImage productImage = new ProductImage();
        productImage.setImageName(fileName);

        Product product = new Product();
        product.setId(productId);
        productImage.setProduct(product);

        productImageRepository.save(productImage);
    }
}
