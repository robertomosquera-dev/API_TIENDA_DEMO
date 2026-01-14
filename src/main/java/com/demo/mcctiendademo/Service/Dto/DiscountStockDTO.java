package com.demo.mcctiendademo.Service.Dto;

import java.util.UUID;

public record DiscountStockDTO(
        UUID productId,
        int quantity
) {
}
