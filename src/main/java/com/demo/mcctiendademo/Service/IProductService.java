package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Http.Request.ProductCreateRequest;
import com.demo.mcctiendademo.Http.Response.ProductCreateResponse;
import com.demo.mcctiendademo.Service.Dto.DetailDiscountStockDTO;
import com.demo.mcctiendademo.Service.Dto.DiscountStockDTO;

import java.util.UUID;


public interface IProductService extends ICRUD<ProductCreateResponse, UUID, ProductCreateRequest> {
    DetailDiscountStockDTO discountStock(DiscountStockDTO discountStockDTO) throws Exception;
}
