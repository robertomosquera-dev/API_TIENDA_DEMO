package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.CustomerOrderView;
import com.demo.mcctiendademo.Entity.PurchaseOrder;
import com.demo.mcctiendademo.Http.Request.OrderCreateRequest;
import com.demo.mcctiendademo.Http.Request.OrderItemCreateRequest;
import com.demo.mcctiendademo.Http.Response.ItemCreateResponse;
import com.demo.mcctiendademo.Http.Response.OrderFind;
import com.demo.mcctiendademo.Http.Response.OrderItemCreateResponse;
import com.demo.mcctiendademo.Mapper.CustomerOrderViewMapper;
import com.demo.mcctiendademo.Repository.ICustomerOrderViewGenericRepository;
import com.demo.mcctiendademo.Service.Dto.DetailDiscountStockDTO;
import com.demo.mcctiendademo.Service.Dto.DiscountStockDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProcessServiceImp implements  IOrderProcessService{

    private final IPurchaseOrderService purchaseOrderService;
    private final IProductService  productService;
    private final IOrderItemService orderItemService;
    private final ICustomerOrderViewGenericRepository customerOrderViewRepository;
    private final CustomerOrderViewMapper orderViewMapper;

    @Override
    @Transactional
    @CacheEvict(
            value = {
                    "orders_all",
                    "orders_by_id",
                    "order_items_by_order_id"
            },
            allEntries = true
    )
    public OrderFind processOrder(OrderCreateRequest orderCreateRequest) throws Exception {

        if(orderCreateRequest == null ) throw new RuntimeException("cannot be null the order");

        PurchaseOrder order =
                purchaseOrderService.save(orderCreateRequest.customerId());

        BigDecimal totalOrder = BigDecimal.ZERO;

        for (ItemCreateResponse item : orderCreateRequest.orderItems()) {

            DetailDiscountStockDTO discount =
                    productService.discountStock(
                            new DiscountStockDTO(item.productId(), item.quantity())
                    );

            OrderItemCreateRequest itemCreateRequest =
                    new OrderItemCreateRequest(
                            item.productId(),
                            order.getId(),
                            item.quantity(),
                            discount.total()
                    );

            orderItemService.save(itemCreateRequest);

            totalOrder = totalOrder.add(discount.total());
        }

        purchaseOrderService.updateTotal(order, totalOrder);

        return new OrderFind(
                order.getId(),
                order.getCustomer().getName(),
                order.getTotal(),
                order.getStateOrder(),
                order.getDateTime()
        );
    }

    @Override
    @Cacheable("orders_all")
    public List<OrderFind> findAll() {
        List<CustomerOrderView> orders = customerOrderViewRepository.findAll();
        List<OrderFind>orderFinds = orderViewMapper.toDto(orders);
        return orderFinds;
    }

    @Cacheable(
            value = "order_items_by_order_id",
            key = "#orderId"
    )
    public List<OrderItemCreateResponse> findItemsByOrderId(UUID orderId) {
        return orderItemService.findByOrderId(orderId);
    }

    @Override
    @Cacheable(
            value = "orders_by_id",
            key = "#id"
    )
    public OrderFind findById(UUID id) throws Exception {
        CustomerOrderView order = customerOrderViewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return orderViewMapper.toDto(order);
    }

}
