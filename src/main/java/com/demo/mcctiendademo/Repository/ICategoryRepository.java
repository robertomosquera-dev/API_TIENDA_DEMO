package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.Category;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends IRepository<Category> {
    Optional<Category> findByName(String name);
}
