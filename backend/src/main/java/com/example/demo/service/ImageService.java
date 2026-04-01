package com.example.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ImageService {
    @Autowired
    private Cloudinary cloudinary;

    //upload file hình riêng lẻ -> trả về map<>
    private Map uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AppException(ErrorCode.EMPTY_FILE);
        }

        try {
            return cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "electro_products"));
        } catch (IOException e) {
            log.error("Cloudinary Upload Error: {}", e.getMessage());
            throw new AppException(ErrorCode.UPLOAD_IMAGE_FAILED);
        }
    }

    public List<Map> uploadMultipleImages(List<MultipartFile> files) throws IOException {
        List<Map> uploadResults = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Map result = uploadImage(file); // Gọi lại hàm upload đơn lẻ đã viết
                uploadResults.add(result);
            }
        }
        return uploadResults;
    }
}
