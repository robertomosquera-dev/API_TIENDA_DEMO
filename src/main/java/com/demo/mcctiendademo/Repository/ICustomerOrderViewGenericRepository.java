package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.CustomerOrderView;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICustomerOrderViewGenericRepository extends IGenericRepository<CustomerOrderView, UUID> {
}
