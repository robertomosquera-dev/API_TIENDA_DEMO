package com.demo.mcctiendademo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "vw_order_product_detail")
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDetailView {

    @Id
    @Column(name = "purchase_order_id")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "state_order")
    private String stateOrder;

    private Integer quantity;

    @Column(name = "total_item")
    private BigDecimal totalItem;

    private String product;
}
