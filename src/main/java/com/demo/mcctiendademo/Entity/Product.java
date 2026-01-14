package com.demo.mcctiendademo.Entity;

import com.demo.mcctiendademo.Entity.Enum.StateDelete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price;
    private Integer stock;
    private String description;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE}
    )
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "category_id"
    )
    private Category category;
    @Enumerated(EnumType.STRING)
    private StateDelete stateDelete = StateDelete.ACTIVE;
}
