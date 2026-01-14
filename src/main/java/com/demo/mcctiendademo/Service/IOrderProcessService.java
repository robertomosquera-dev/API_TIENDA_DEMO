package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Http.Request.OrderCreateRequest;
import com.demo.mcctiendademo.Http.Response.OrderFind;
import com.demo.mcctiendademo.Http.Response.OrderItemCreateResponse;

import java.util.List;
import java.util.UUID;

public interface IOrderProcessService {
    OrderFind processOrder(OrderCreateRequest orderCreateRequest) throws Exception;
    List<OrderFind> findAll();
    List<OrderItemCreateResponse> findItemsByOrderId(UUID orderId);
    OrderFind findById(UUID id) throws Exception;

}
