package com.demo.mcctiendademo.Http.Response;

import com.demo.mcctiendademo.Entity.Enum.StateOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderFind(
        UUID orderId,
        String customerName,
        BigDecimal total,
        StateOrder order,
        LocalDateTime dateTime
) {
}
