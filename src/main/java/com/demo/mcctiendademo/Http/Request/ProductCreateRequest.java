package com.demo.mcctiendademo.Http.Request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateRequest(
        String name,
        BigDecimal price,
        String description,
        Integer stock,
        UUID categoryId
) {
}
