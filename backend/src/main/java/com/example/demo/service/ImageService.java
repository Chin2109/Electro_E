package com.example.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.dto.response.product.CloudinaryResponse;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ImageService {
    @Autowired
    private Cloudinary cloudinary;


    public CloudinaryResponse uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.EMPTY_FILE);
        }

        try {
            Map map = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "electro_products"));

            return CloudinaryResponse.builder()
                    .publicId((String) map.get("public_id"))
                    .url((String) map.get("secure_url"))
                    .build();
        } catch (IOException e) {
            log.error("Cloudinary Upload Error: {}", e.getMessage());
            throw new AppException(ErrorCode.UPLOAD_IMAGE_FAILED);
        }
    }

    public List<CloudinaryResponse> uploadMultipleImages(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new AppException(ErrorCode.EMPTY_FILE);
        }

        List<CloudinaryResponse> uploadResults = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                CloudinaryResponse result = uploadImage(file);
                uploadResults.add(result);
            }
        }
        return uploadResults;
    }
}
