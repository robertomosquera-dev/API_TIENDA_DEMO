package com.demo.mcctiendademo.Service;


import com.demo.mcctiendademo.Entity.PurchaseOrder;

import java.math.BigDecimal;
import java.util.UUID;

public interface IPurchaseOrderService extends ICRUD<PurchaseOrder, UUID,UUID> {

    PurchaseOrder updateTotal(PurchaseOrder order, BigDecimal total) throws Exception;


}
