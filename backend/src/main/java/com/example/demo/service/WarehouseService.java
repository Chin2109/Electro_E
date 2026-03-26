package com.example.demo.service;

import com.example.demo.dto.request.warehouse.CreateWarehouseRequest;
import com.example.demo.entity.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public Warehouse createWarehouse(CreateWarehouseRequest request) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.getName());
        warehouse.setType(request.getType());
        warehouse.setPhone(warehouse.getPhone());
        warehouse.setAddress(warehouse.getAddress());
        warehouse.setStatus(1L);

        return warehouseRepository.save(warehouse);
    }

}
