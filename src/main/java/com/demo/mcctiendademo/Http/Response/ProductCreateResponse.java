package com.demo.mcctiendademo.Http.Response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateResponse(
        UUID id,
        String name,
        BigDecimal price,
        Integer stock,
        String description,
        UUID categoryId
) {
}
