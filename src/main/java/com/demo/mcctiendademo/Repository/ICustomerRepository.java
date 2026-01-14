package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICustomerRepository extends IRepository<Customer, UUID>{
}
