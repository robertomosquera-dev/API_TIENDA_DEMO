package com.demo.mcctiendademo.Service;


import com.demo.mcctiendademo.Http.Request.CategoryCreateRequest;
import com.demo.mcctiendademo.Http.Response.CategoryCreateResponse;

public interface ICategoryService extends IService<CategoryCreateResponse, CategoryCreateRequest>{
    CategoryCreateResponse findByName(String name) throws Exception;
}
