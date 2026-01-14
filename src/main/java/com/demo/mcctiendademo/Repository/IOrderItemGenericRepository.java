package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderItemGenericRepository extends IGenericRepository<OrderItem,UUID> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId")
    List<OrderItem> findAllByOrderId(@Param("orderId") UUID orderId);


}
