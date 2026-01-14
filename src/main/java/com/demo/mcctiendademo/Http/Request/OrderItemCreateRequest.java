package com.demo.mcctiendademo.Http.Request;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemCreateRequest(
        UUID productId,
        UUID orderId,
        int quantity,
        BigDecimal total_item
) {
}
