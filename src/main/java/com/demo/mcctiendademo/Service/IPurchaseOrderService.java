package com.demo.mcctiendademo.Service;


import com.demo.mcctiendademo.Entity.PurchaseOrder;
import com.demo.mcctiendademo.Http.Request.OrderCreateRequest;
import com.demo.mcctiendademo.Http.Response.OrderCreateResponse;
import com.demo.mcctiendademo.Http.Response.OrderFind;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IPurchaseOrderService extends IService<PurchaseOrder, UUID>{

    PurchaseOrder updateTotal(PurchaseOrder order, BigDecimal total) throws Exception;


}
