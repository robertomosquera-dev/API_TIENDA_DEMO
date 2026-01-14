package com.demo.mcctiendademo.Service;

import java.util.List;
import java.util.UUID;

public interface IService <T,D>{
    T save(D dto) throws Exception;
    List<T> saveAll(List<D> dtoList) throws Exception;
    T findById(UUID id) throws Exception;
    List<T> findAll() throws Exception;
    void delete(UUID id) throws Exception;
}
