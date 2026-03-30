package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.warranty.CreateWarrantyRequest;
import com.example.demo.service.WarrantyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/warranty")
public class WarrantyController {
    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    @PostMapping("/create")
    ApiResponse<String> createWarrantyPolicy(@RequestBody CreateWarrantyRequest request) {
        return ApiResponse.<String>builder()
                .message("New Warranty Policy")
                .data(warrantyService.createWarrantyPolicy(request))
                .build();
    }
}
