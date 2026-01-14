package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICategoryRepository extends IRepository<Category, UUID> {
    Optional<Category> findByName(String name);
}
