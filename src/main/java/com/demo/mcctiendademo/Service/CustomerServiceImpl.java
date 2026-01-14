package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.Customer;
import com.demo.mcctiendademo.Http.Request.CustomerCreateRequest;
import com.demo.mcctiendademo.Http.Response.CustomerCreateResponse;
import com.demo.mcctiendademo.Mapper.CustomerMapper;
import com.demo.mcctiendademo.Repository.ICustomerGenericRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private final ICustomerGenericRepository repository;
    private final CustomerMapper mapper;

    @Override
    @CachePut(
            value = "customers_by_id",
            key = "#result.id"
    )
    @CacheEvict(
            value = {
                    "customers_all"
            },
            allEntries = true
    )
    public CustomerCreateResponse save(CustomerCreateRequest dto) throws Exception {
        Customer customer = mapper.toEntity(dto);
        customer = repository.save(customer);
        return mapper.toDto(customer);
    }

    @Override
    @CacheEvict(
            value = {
                    "customers_all",
                    "customers_by_id"
            },
            allEntries = true
    )
    public List<CustomerCreateResponse> saveAll(List<CustomerCreateRequest> dtoList) throws Exception {
        List<Customer> customers = mapper.toEntityList(dtoList);
        customers = repository.saveAll(customers);
        return mapper.toDtoList(customers);
    }

    @Override
    @Cacheable(
            value = "customers_by_id",
            key = "#id"
    )
    public CustomerCreateResponse findById(UUID id) throws Exception {
        Customer customer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return mapper.toDto(customer);
    }

    @Override
    @Cacheable("customers_all")
    public List<CustomerCreateResponse> findAll() throws Exception {
        List<Customer> customers = repository.findAll();
        return mapper.toDtoList(customers);
    }

    @Override
    @CacheEvict(
            value = {
                    "customers_all",
                    "customers_by_id"
            },
            allEntries = true
    )
    public void delete(UUID id) throws Exception {
        repository.deleteById(id);
    }

    public Customer findEntityById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

}
