package com.demo.mcctiendademo.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "vw_product_with_category")
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductWithCategoryView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private UUID id;
    private String product;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;
    private Integer stock;
    private String category;
}
