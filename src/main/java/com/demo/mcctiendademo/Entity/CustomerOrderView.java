package com.demo.mcctiendademo.Entity;

import com.demo.mcctiendademo.Entity.Enum.StateOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vw_customer_with_orders")
@Immutable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class CustomerOrderView {

    @Id
    @Column(name = "purchase_order_id")
    @EqualsAndHashCode.Include
    private UUID id;

    private String customer;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_order")
    private StateOrder stateOrder;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private BigDecimal total;
}
