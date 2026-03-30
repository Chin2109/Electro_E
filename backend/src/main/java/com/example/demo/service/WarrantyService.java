package com.example.demo.service;

import com.example.demo.dto.request.warranty.AddWarrantyProductRequest;
import com.example.demo.dto.request.warranty.CreateWarrantyRequest;
import com.example.demo.entity.WarrantyPolicy;
import com.example.demo.entity.WarrantyPolicyProduct;
import com.example.demo.entity.WarrantyPolicyProductId;
import com.example.demo.repository.WarrantyPolicyProductRepository;
import com.example.demo.repository.WarrantyPolicyRepository;
import org.springframework.stereotype.Service;

@Service
public class WarrantyService {
    private final WarrantyPolicyRepository warrantyPolicyRepository;
    private final WarrantyPolicyProductRepository warrantyPolicyProductRepository;

    public WarrantyService(WarrantyPolicyRepository warrantyPolicyRepository,
                           WarrantyPolicyProductRepository warrantyPolicyProductRepository) {
        this.warrantyPolicyRepository = warrantyPolicyRepository;
        this.warrantyPolicyProductRepository = warrantyPolicyProductRepository;
    }

    public String createWarrantyPolicy(CreateWarrantyRequest request) {
        WarrantyPolicy warrantyPolicy = new WarrantyPolicy();
        warrantyPolicy.setName(request.getName());
        warrantyPolicy.setDescription(request.getDescription());
        warrantyPolicy.setDurationMonths(request.getDurationMonths());
        warrantyPolicy.setStatus(1L);
        warrantyPolicyRepository.save(warrantyPolicy);

        return "Created";
    }

    public String addWarrantyToProduct(AddWarrantyProductRequest request) {
        WarrantyPolicyProductId id = new WarrantyPolicyProductId(request.getWarrantyId(),
                request.getProductId());

        WarrantyPolicyProduct warProd = new WarrantyPolicyProduct();
        warProd.setId(id);
        warProd.setPrice(request.getPrice());
        warrantyPolicyProductRepository.save(warProd);

        return "Created";
    }
}
