package com.demo.mcctiendademo.Service;

import com.demo.mcctiendademo.Entity.Category;
import com.demo.mcctiendademo.Http.Request.CategoryCreateRequest;
import com.demo.mcctiendademo.Http.Response.CategoryCreateResponse;
import com.demo.mcctiendademo.Mapper.CategoryMapper;
import com.demo.mcctiendademo.Repository.ICategoryGenericRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements ICategoryService{

    private final ICategoryGenericRepository repository;
    private final CategoryMapper mapper;

    @Override
    @Cacheable(
            value = "categories_by_id",
            key = "#id"
    )
    public CategoryCreateResponse findById(UUID id) throws Exception {
        log.debug("Buscando categoria por id: {}", id);
        if(id == null) {
            log.error("id cannot be null");
            throw new RuntimeException("id cannot be null");
        }
        Category category = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        log.debug("Categoria encontrada: {}", category);
        return mapper.toDto(category);
    }


    @Override
    @Cacheable(
            value = "categories_by_name",
            key = "#name.toLowerCase().trim()"
    )
    public CategoryCreateResponse findByName(String name) throws Exception {
        Category category = repository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return mapper.toDto(category);
    }

    @Override
    @CachePut(
            value = "categories_by_id",
            key = "#result.id"
    )
    @CacheEvict(
            value = {
                    "categories_all",
                    "categories_by_name"
            },
            allEntries = true
    )
    public CategoryCreateResponse save(CategoryCreateRequest dto) throws Exception {
        Category category = mapper.toEntity(dto);
        category = repository.save(category);
        return mapper.toDto(category);
    }

    @Override
    @CacheEvict(
            value = {
                    "categories_all",
                    "categories_by_id",
                    "categories_by_name"
            },
            allEntries = true
    )
    public List<CategoryCreateResponse> saveAll(List<CategoryCreateRequest> dtoList) throws Exception {
        List<Category> categories = mapper.toEntityList(dtoList);
        categories = repository.saveAll(categories);
        return mapper.toDtoList(categories);
    }

    @Override
    @Cacheable("categories_all")
    public List<CategoryCreateResponse> findAll() throws Exception {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    @CacheEvict(
            value = {
                    "categories_all",
                    "categories_by_id",
                    "categories_by_name"
            },
            allEntries = true
    )
    public void delete(UUID id) throws Exception {
        repository.deleteById(id);
    }
}
