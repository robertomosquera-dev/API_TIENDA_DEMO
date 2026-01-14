package com.demo.mcctiendademo.Http.Response;

import java.util.UUID;

public record CategoryCreateResponse(
        UUID id,
        String name
) {
}
