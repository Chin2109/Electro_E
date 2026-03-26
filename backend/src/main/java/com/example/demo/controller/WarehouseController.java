package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.warehouse.CreateWarehouseRequest;
import com.example.demo.entity.Warehouse;
import com.example.demo.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    //method này cần bổ sung: check đầu vào + người dùng gửi long lad của cửa hàng
    @PostMapping("/create")
    public ApiResponse<Warehouse> create(@RequestBody CreateWarehouseRequest request) {
        return ApiResponse.<Warehouse>builder()
                .message("New warehouse created successfully")
                .data(warehouseService.createWarehouse(request))
                .build();
    }

    //get all warehouse and details
    @GetMapping()
    public ApiResponse<List<Warehouse>> getALl() {
        return ApiResponse.<List<Warehouse>>builder()
                .message("Warehouse list")
                .data(warehouseService.getAll())
                .build();
    }

    //update

    //delete (for testing)
}
