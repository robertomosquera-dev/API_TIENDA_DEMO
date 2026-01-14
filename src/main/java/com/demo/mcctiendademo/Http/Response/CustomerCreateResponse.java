package com.demo.mcctiendademo.Http.Response;

import java.util.UUID;

public record CustomerCreateResponse(
        UUID id,
        String email,
        String name
) {
}
