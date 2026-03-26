package com.example.demo.dto.request.warehouse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateWarehouseRequest {
    String name;
    String type;
    String phone;
    String address;
}
