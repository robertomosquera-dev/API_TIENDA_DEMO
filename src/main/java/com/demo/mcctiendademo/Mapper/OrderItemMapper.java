package com.demo.mcctiendademo.Mapper;

import com.demo.mcctiendademo.Entity.OrderItem;
import com.demo.mcctiendademo.Entity.Product;
import com.demo.mcctiendademo.Entity.PurchaseOrder;
import com.demo.mcctiendademo.Http.Request.OrderItemCreateRequest;
import com.demo.mcctiendademo.Http.Response.OrderItemCreateResponse;
import com.demo.mcctiendademo.Service.Dto.DiscountStockDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {


    // ENTITY -> DTO
    @Mapping(source = "product", target = "productId", qualifiedByName = "productToId")
    @Mapping(source = "order", target = "orderId", qualifiedByName = "orderToId")
    OrderItemCreateResponse toDto(OrderItem orderItem);

    // DTO -> ENTITY
    @Mapping(source = "productId", target = "product", qualifiedByName = "idToProduct")
    @Mapping(source = "orderId", target = "order", qualifiedByName = "idToOrder")
    OrderItem toEntity(OrderItemCreateRequest dto);


    List<OrderItemCreateResponse> toDtoList(List<OrderItem> orderItems);

    // ---------- Helpers ----------

    @Named("idToProduct")
    default Product idToProduct(UUID productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    @Named("idToOrder")
    default PurchaseOrder idToOrder(UUID orderId) {
        if (orderId == null) return null;
        PurchaseOrder order = new PurchaseOrder();
        order.setId(orderId);
        return order;
    }

    @Named("productToId")
    default UUID productToId(Product product) {
        return product != null ? product.getId() : null;
    }

    @Named("orderToId")
    default UUID orderToId(PurchaseOrder order) {
        return order != null ? order.getId() : null;
    }

}
