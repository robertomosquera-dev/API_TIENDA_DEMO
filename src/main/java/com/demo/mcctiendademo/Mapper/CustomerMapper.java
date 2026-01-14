package com.demo.mcctiendademo.Mapper;

import com.demo.mcctiendademo.Entity.Customer;
import com.demo.mcctiendademo.Http.Request.CustomerCreateRequest;
import com.demo.mcctiendademo.Http.Response.CustomerCreateResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerCreateRequest customerCreateRequest);
    List<Customer> toEntityList(List<CustomerCreateRequest> customerCreateRequest);
    CustomerCreateResponse toDto(Customer customer);
    List<CustomerCreateResponse> toDtoList(List<Customer> customer);
}
