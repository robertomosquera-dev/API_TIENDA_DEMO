package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.Product;
import com.demo.mcctiendademo.Http.Request.ProductCreateRequest;
import com.demo.mcctiendademo.Http.Response.ProductCreateResponse;
import com.demo.mcctiendademo.Mapper.ProductMapper;
import com.demo.mcctiendademo.Repository.IProductGenericRepository;
import com.demo.mcctiendademo.Service.Dto.DetailDiscountStockDTO;
import com.demo.mcctiendademo.Service.Dto.DiscountStockDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductGenericRepository repository;
    private final ProductMapper mapper;

    @Override
    @CachePut(
            value = "products_by_id",
            key = "#result.id()"
    )
    @CacheEvict(
            value = {
                    "products_all"
            },
            allEntries = true
    )
    public ProductCreateResponse save(ProductCreateRequest dto) throws Exception {
        Product product = mapper.toEntity(dto);
        product = repository.save(product);
        return mapper.toDto(product);
    }

    @Override
    @CacheEvict(
            value = {
                    "products_by_id",
                    "products_all"
            },
            allEntries = true
    )
    public List<ProductCreateResponse> saveAll(List<ProductCreateRequest> dtoList) throws Exception {
        List<Product> products = mapper.toEntityList(dtoList);
        products = repository.saveAll(products);
        return mapper.toDtoList(products);
    }

    @Override
    @Cacheable(
            value = "products_by_id",
            key = "#id"
    )
    public ProductCreateResponse findById(UUID id) throws Exception {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return mapper.toDto(product);
    }

    @Override
    @Cacheable("products_all")
    public List<ProductCreateResponse> findAll() throws Exception {
        List<Product> products = repository.findAll();
        return mapper.toDtoList(products);
    }

    @Override
    @CacheEvict(
            value = {
                    "products_by_id",
                    "products_all"
            },
            allEntries = true
    )
    public void delete(UUID id) throws Exception {
        repository.deleteById(id);
    }


    //Este es un servicio que lo usa otro servicio cordinador
    @Override
    @CacheEvict(
            value = {
                    "products_by_id",
                    "products_all",
            },
            key = "#discountStockDTO.productId()",
            allEntries = true
    )
    public DetailDiscountStockDTO discountStock(DiscountStockDTO discountStockDTO) throws Exception {

        Product product = repository.findById(discountStockDTO.productId())
                .orElseThrow(() -> new RuntimeException("product not found"));

        if (product.getStock() < discountStockDTO.quantity())
            throw new RuntimeException("Insufficient stock");

        BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(discountStockDTO.quantity()));

        product.setStock(product.getStock() - discountStockDTO.quantity());

        repository.save(product);

        return new DetailDiscountStockDTO(product.getId(), discountStockDTO.quantity(), total);
    }

}
