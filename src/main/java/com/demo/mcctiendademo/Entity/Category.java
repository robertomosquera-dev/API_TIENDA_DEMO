package com.demo.mcctiendademo.Entity;

import com.demo.mcctiendademo.Entity.Enum.StateDelete;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private StateDelete stateDelete = StateDelete.ACTIVE;
}
