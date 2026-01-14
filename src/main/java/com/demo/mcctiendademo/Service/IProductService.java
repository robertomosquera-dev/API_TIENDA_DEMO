package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Http.Request.ProductCreateRequest;
import com.demo.mcctiendademo.Http.Response.ProductCreateResponse;
import com.demo.mcctiendademo.Service.Dto.DetailDiscountStockDTO;
import com.demo.mcctiendademo.Service.Dto.DiscountStockDTO;


public interface IProductService extends IService<ProductCreateResponse, ProductCreateRequest>{
    DetailDiscountStockDTO discountStock(DiscountStockDTO discountStockDTO) throws Exception;
}
