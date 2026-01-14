package com.demo.mcctiendademo.Mapper;

import com.demo.mcctiendademo.Entity.Category;
import com.demo.mcctiendademo.Entity.Product;
import com.demo.mcctiendademo.Http.Request.ProductCreateRequest;
import com.demo.mcctiendademo.Http.Response.ProductCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "categoryId",target = "category",qualifiedByName = "mapCategory")
    Product toEntity(ProductCreateRequest productCreateRequest);

    @Mapping(source = "category",target = "categoryId",qualifiedByName = "mapCategoryId")
    ProductCreateResponse toDto(Product product);

    @Named("mapCategory")
    default Category mapCategory(UUID categoryId){
        if(categoryId == null) return null;
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    @Named("mapCategoryId")
    default UUID mapCategoryId(Category category){
        if(category == null) return null;
        return category.getId();
    }

    List<Product> toEntityList(List<ProductCreateRequest> productCreateRequest);
    List<ProductCreateResponse> toDtoList(List<Product> product);

}
