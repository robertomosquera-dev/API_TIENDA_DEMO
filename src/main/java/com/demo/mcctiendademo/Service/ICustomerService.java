package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.Customer;
import com.demo.mcctiendademo.Http.Request.CustomerCreateRequest;
import com.demo.mcctiendademo.Http.Response.CustomerCreateResponse;

import java.util.UUID;

public interface ICustomerService extends IService<CustomerCreateResponse, CustomerCreateRequest> {
    Customer findEntityById(UUID id);
}
