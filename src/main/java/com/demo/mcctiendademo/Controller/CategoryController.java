package com.demo.mcctiendademo.Controller;

import com.demo.mcctiendademo.Http.Request.CategoryCreateRequest;
import com.demo.mcctiendademo.Http.Response.CategoryCreateResponse;
import com.demo.mcctiendademo.Service.ICategoryService;
import com.demo.mcctiendademo.Util.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${version.api}/category")
@RequiredArgsConstructor
public class CategoryController implements IController<CategoryCreateRequest>{

    private final ICategoryService service;

    @PostMapping("/save")
    @Override
    public ResponseEntity<?> saveEndPoint(@RequestBody CategoryCreateRequest data) throws Exception {
        CategoryCreateResponse category = service.save(data);
        System.out.println(category.name());
        SuccessMessage<CategoryCreateResponse> successMessage = SuccessMessage.success(HttpStatus.CREATED,"recurse create",category);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    @PostMapping("/saveAll")
    @Override
    public ResponseEntity<?> saveAllEndPoint(@RequestBody List<CategoryCreateRequest> listData) throws Exception {
        List<CategoryCreateResponse> listCategory = service.saveAll(listData);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.CREATED,"list create",listCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<?> getAllEndPoint() throws Exception {
        List<CategoryCreateResponse> listCategory = service.findAll();
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.OK,"list get",listCategory);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> getByIdEndPoint(@PathVariable UUID id) throws Exception {
        CategoryCreateResponse category = service.findById(id);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.OK,"recurse get",category);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteEndPoint(@PathVariable UUID id) throws Exception {
        service.delete(id);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.NO_CONTENT,"recurse delete",null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
    }

}
