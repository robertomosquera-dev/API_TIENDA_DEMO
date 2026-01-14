package com.demo.mcctiendademo.Service.Dto;

import java.math.BigDecimal;
import java.util.UUID;

public record DetailDiscountStockDTO(
        UUID productId,
        int quantity,
        BigDecimal total
) {
}
