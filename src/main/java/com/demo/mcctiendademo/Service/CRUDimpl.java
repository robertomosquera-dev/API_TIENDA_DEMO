package com.demo.mcctiendademo.Service;


import com.demo.mcctiendademo.Repository.IGenericRepository;

import java.util.List;

public abstract class CRUDimpl<T,ID,D> implements ICRUD <T,ID,D>{


    protected IGenericRepository<T,ID> repository;

    @Override
    public T save(D dto) throws Exception {
        return null;
    }

    @Override
    public List<T> saveAll(List<D> dtoList) throws Exception {
        return List.of();
    }

    @Override
    public T findById(ID id) throws Exception {
        return null;
    }

    @Override
    public List<T> findAll() throws Exception {
        return List.of();
    }

    @Override
    public void delete(ID id) throws Exception {

    }

}
