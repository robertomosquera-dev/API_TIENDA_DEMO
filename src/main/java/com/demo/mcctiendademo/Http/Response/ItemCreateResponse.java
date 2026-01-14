package com.demo.mcctiendademo.Http.Response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemCreateResponse(
        UUID productId,
        int quantity
) {

}
