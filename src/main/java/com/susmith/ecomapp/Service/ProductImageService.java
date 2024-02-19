package com.susmith.ecomapp.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductImageService {
    void saveImage(MultipartFile file, Long productId) throws IOException;
}
