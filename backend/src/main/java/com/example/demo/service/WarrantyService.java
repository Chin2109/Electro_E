package com.example.demo.service;

import com.example.demo.dto.request.warranty.CreateWarrantyRequest;
import com.example.demo.entity.WarrantyPolicy;
import com.example.demo.repository.WarrantyPolicyRepository;
import org.springframework.stereotype.Service;

@Service
public class WarrantyService {
    private final WarrantyPolicyRepository warrantyPolicyRepository;

    public WarrantyService(WarrantyPolicyRepository warrantyPolicyRepository) {
        this.warrantyPolicyRepository = warrantyPolicyRepository;
    }

    public String createWarrantyPolicy(CreateWarrantyRequest request) {
        WarrantyPolicy warrantyPolicy = new WarrantyPolicy();
        warrantyPolicy.setName(request.getName());
        warrantyPolicy.setDescription(request.getDescription());
    }
}
