package com.demo.mcctiendademo.Service;


import com.demo.mcctiendademo.Http.Request.CategoryCreateRequest;
import com.demo.mcctiendademo.Http.Response.CategoryCreateResponse;

import java.util.UUID;

public interface ICategoryService extends ICRUD<CategoryCreateResponse,UUID,CategoryCreateRequest> {
    CategoryCreateResponse findByName(String name) throws Exception;
}
