package com.demo.mcctiendademo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface IRepository<T>extends JpaRepository<T, UUID> {
}
