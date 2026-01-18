package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends IGenericRepository<UserEntity, UUID>{
    Optional<UserEntity> findUserByUsername(String username);
}
