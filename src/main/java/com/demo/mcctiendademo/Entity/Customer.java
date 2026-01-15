package com.demo.mcctiendademo.Entity;

import com.demo.mcctiendademo.Entity.Enum.StateDelete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private StateDelete stateDelete = StateDelete.ACTIVE;

    @OneToOne(
            cascade = {CascadeType.MERGE,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    private User user;

}
