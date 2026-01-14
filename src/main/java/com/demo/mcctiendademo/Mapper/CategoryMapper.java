package com.demo.mcctiendademo.Mapper;

import com.demo.mcctiendademo.Entity.Category;
import com.demo.mcctiendademo.Http.Request.CategoryCreateRequest;
import com.demo.mcctiendademo.Http.Response.CategoryCreateResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryCreateRequest categoryCreateRequest);
    List<Category> toEntityList(List<CategoryCreateRequest> categoryCreateRequest);

    CategoryCreateResponse toDto(Category category);
    List<CategoryCreateResponse> toDtoList(List<Category> category);

}
