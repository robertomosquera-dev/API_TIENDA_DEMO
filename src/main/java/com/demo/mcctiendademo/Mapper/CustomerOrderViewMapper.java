package com.demo.mcctiendademo.Mapper;

import com.demo.mcctiendademo.Entity.CustomerOrderView;
import com.demo.mcctiendademo.Http.Response.OrderFind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerOrderViewMapper {

    @Mapping(source = "id",target = "orderId")
    @Mapping(source = "customer",target = "customerName")
    @Mapping(source = "dateTime",target = "dateTime")
    @Mapping(source = "stateOrder",target = "order")
    OrderFind toDto(CustomerOrderView customerOrderView);

    List<OrderFind> toDto(List<CustomerOrderView> customerOrderViews);

}
