package com.demo.mcctiendademo.Http.Response;

import com.demo.mcctiendademo.Entity.Enum.StateOrder;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderCreateResponse(
    UUID orderId,
    UUID customerId,
    BigDecimal total,
    StateOrder order,
    LocalDateTime dateTime,
    List<OrderItemCreateResponse>orderItemList
) {
}
