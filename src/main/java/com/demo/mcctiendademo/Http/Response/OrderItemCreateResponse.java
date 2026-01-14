package com.demo.mcctiendademo.Http.Response;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemCreateResponse(
        UUID id,
        UUID productId,
        UUID orderId,
        int quantity,
        BigDecimal total_item
) {
}
