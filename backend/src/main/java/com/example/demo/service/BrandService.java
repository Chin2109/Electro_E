package com.example.demo.service;

import com.example.demo.dto.request.brand.BrandCreationRequest;
import com.example.demo.entity.Brands;
import com.example.demo.repository.BrandsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    private final BrandsRepository brandsRepository;

    public BrandService(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    public List<Brands> createBrand(BrandCreationRequest request) {
        Brands brand = new Brands();
        brand.setName(request.getName());

        Brands savedBrand = brandsRepository.save(brand);
        List<Brands> returnList = new ArrayList<>();
        returnList.add(savedBrand);

        return returnList;
    }
}
