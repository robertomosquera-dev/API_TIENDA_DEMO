package com.demo.mcctiendademo.Service;


import com.demo.mcctiendademo.Http.Request.OrderItemCreateRequest;
import com.demo.mcctiendademo.Http.Response.OrderItemCreateResponse;

import java.util.List;
import java.util.UUID;


public interface IOrderItemService extends IService<OrderItemCreateResponse, OrderItemCreateRequest>{
    List<OrderItemCreateResponse> findByOrderId(UUID orderId);
}
