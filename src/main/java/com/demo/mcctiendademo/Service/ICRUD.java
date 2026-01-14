package com.demo.mcctiendademo.Service;

import java.util.List;

public interface ICRUD<T,ID,D>{
    T save(D dto) throws Exception;
    List<T> saveAll(List<D> dtoList) throws Exception;
    T findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
    void delete(ID id) throws Exception;
}
