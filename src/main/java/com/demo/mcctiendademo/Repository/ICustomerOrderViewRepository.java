package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.CustomerOrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICustomerOrderViewRepository extends JpaRepository<CustomerOrderView, UUID> {
}
