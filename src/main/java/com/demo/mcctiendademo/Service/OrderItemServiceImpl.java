package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.OrderItem;
import com.demo.mcctiendademo.Http.Request.OrderItemCreateRequest;
import com.demo.mcctiendademo.Http.Response.OrderItemCreateResponse;
import com.demo.mcctiendademo.Mapper.OrderItemMapper;
import com.demo.mcctiendademo.Repository.IOrderItemGenericRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService{

    private final IOrderItemGenericRepository repository;
    private final OrderItemMapper mapper;

    @Override
    public OrderItemCreateResponse save(OrderItemCreateRequest dto) throws Exception {
        OrderItem orderItem = mapper.toEntity(dto);
        orderItem =  repository.save(orderItem);
        return mapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemCreateResponse> saveAll(List<OrderItemCreateRequest> dtoList) throws Exception {
        return List.of();
    }

    @Override
    public OrderItemCreateResponse findById(UUID id) throws Exception {
        OrderItem orderItem = repository.findById(id).orElse(new OrderItem());
        return mapper.toDto(orderItem);
    }

    @Override
    public List<OrderItemCreateResponse> findAll() throws Exception {
        List<OrderItem> orderItems = repository.findAll();
        return  mapper.toDtoList(orderItems);
    }

    @Override
    public void delete(UUID id) throws Exception {
        repository.deleteById(id);
    }

    @Override
    public List<OrderItemCreateResponse> findByOrderId(UUID orderId) {
        List<OrderItem>orderItems = repository.findAllByOrderId(orderId);
        return mapper.toDtoList(orderItems);
    }
}

