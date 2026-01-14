package com.demo.mcctiendademo.Controller;

import com.demo.mcctiendademo.Http.Request.ProductCreateRequest;
import com.demo.mcctiendademo.Http.Response.ProductCreateResponse;
import com.demo.mcctiendademo.Service.IProductService;
import com.demo.mcctiendademo.Util.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("${version.api}/product")
@RequiredArgsConstructor
public class ProductController implements IController<ProductCreateRequest>{

    private final IProductService productService;

    @PostMapping("/save")
    @Override
    public ResponseEntity<SuccessMessage<ProductCreateResponse>> saveEndPoint(
            @RequestBody ProductCreateRequest data) throws Exception {

        ProductCreateResponse response = productService.save(data);
        System.out.println("Controller : "+response.id());
        SuccessMessage<ProductCreateResponse> message =
                SuccessMessage.success(HttpStatus.CREATED, "resource created", response);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/saveAll")
    @Override
    public ResponseEntity<SuccessMessage<List<ProductCreateResponse>>> saveAllEndPoint(
            @RequestBody List<ProductCreateRequest> listData) throws Exception {

        List<ProductCreateResponse> response = productService.saveAll(listData);
        SuccessMessage<List<ProductCreateResponse>> message =
                SuccessMessage.success(HttpStatus.OK, "resource saveAll", response);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<SuccessMessage<List<ProductCreateResponse>>> getAllEndPoint() throws Exception {

        List<ProductCreateResponse> response = productService.findAll();
        SuccessMessage<List<ProductCreateResponse>> message =
                SuccessMessage.success(HttpStatus.OK, "resource getAll", response);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<SuccessMessage<ProductCreateResponse>> getByIdEndPoint(
            @PathVariable UUID id) throws Exception {

        ProductCreateResponse response = productService.findById(id);
        SuccessMessage<ProductCreateResponse> message =
                SuccessMessage.success(HttpStatus.OK, "resource getById", response);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<SuccessMessage<Void>> deleteEndPoint(
            @PathVariable UUID id) throws Exception {

        productService.delete(id);
        SuccessMessage<Void> message =
                SuccessMessage.success(HttpStatus.OK, "resource deleteById", null);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
