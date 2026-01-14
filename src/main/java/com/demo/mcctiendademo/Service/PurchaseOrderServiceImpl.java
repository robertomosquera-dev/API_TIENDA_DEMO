package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.Customer;
import com.demo.mcctiendademo.Entity.Enum.StateOrder;
import com.demo.mcctiendademo.Entity.PurchaseOrder;
import com.demo.mcctiendademo.Repository.IPurchaseOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements IPurchaseOrderService{

    private final ICustomerService customerService;
    private final IPurchaseOrderRepository repository;

    @Override
    public PurchaseOrder save(UUID customer_id) throws Exception {
        Customer customer = customerService.findEntityById(customer_id);
        PurchaseOrder order = new PurchaseOrder();
        order.setCustomer(customer);
        order.setStateOrder(StateOrder.PENDING);
        order.setTotal(BigDecimal.ZERO);
        return repository.save(order);
    }

    @Override
    public PurchaseOrder updateTotal(PurchaseOrder order, BigDecimal total) {
        order.setTotal(total);
        return repository.save(order);
    }

    @Override
    public List<PurchaseOrder> saveAll(List<UUID> dtoList) throws Exception {
        return List.of();
    }

    @Override
    public PurchaseOrder findById(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<PurchaseOrder> findAll() throws Exception {
        return null;
    }

    @Override
    public void delete(UUID id) throws Exception {
        PurchaseOrder order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        repository.delete(order);
    }
}
