package com.demo.mcctiendademo.Http.Request;

import com.demo.mcctiendademo.Http.Response.ItemCreateResponse;

import java.util.List;
import java.util.UUID;

public record OrderCreateRequest(
        UUID customerId,
        List<ItemCreateResponse>orderItems
){
}
