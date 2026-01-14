package com.demo.mcctiendademo.Repository;

import com.demo.mcctiendademo.Entity.PurchaseOrder;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPurchaseOrderGenericRepository extends IGenericRepository<PurchaseOrder, UUID> {
}
