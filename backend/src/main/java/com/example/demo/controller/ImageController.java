package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.response.product.CloudinaryResponse;
import com.example.demo.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ApiResponse<List<CloudinaryResponse>> uploadImages(@RequestParam("files") List<MultipartFile> files) {
        List<CloudinaryResponse> responses = imageService.uploadMultipleImages(files);

        return ApiResponse.<List<CloudinaryResponse>>builder()
                .message("Uploaded successfully " + responses.size() + " files.")
                .data(responses)
                .build();
    }


}
