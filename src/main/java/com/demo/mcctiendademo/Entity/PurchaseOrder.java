package com.demo.mcctiendademo.Entity;

import com.demo.mcctiendademo.Entity.Enum.StateOrder;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_order_id")
    @EqualsAndHashCode.Include
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StateOrder stateOrder = StateOrder.PENDING;

    @ManyToOne()
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customer_id"
    )
    private Customer customer;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

}
