package com.example.demo.dto.request.warranty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateWarrantyRequest {
    String name;
    Long durationMonths;
    String description;
}
