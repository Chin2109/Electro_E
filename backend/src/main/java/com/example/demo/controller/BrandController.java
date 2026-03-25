package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.brand.BrandCreationRequest;
import com.example.demo.entity.Brands;
import com.example.demo.service.BrandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    //create Brand
    @PostMapping("/create")
    ApiResponse<List<Brands>> createBrand(@RequestBody BrandCreationRequest request) {
        return ApiResponse.<List<Brands>>builder()
                .message("Brand created successfully!")
                .data(brandService.createBrand(request))
                .build();
    }

    //get all brands

}
