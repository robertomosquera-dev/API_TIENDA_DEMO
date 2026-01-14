package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductGenericRepository extends IGenericRepository<Product, UUID> {
}
