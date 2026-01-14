package com.demo.mcctiendademo.Controller;


import com.demo.mcctiendademo.Http.Request.CustomerCreateRequest;
import com.demo.mcctiendademo.Http.Response.CustomerCreateResponse;
import com.demo.mcctiendademo.Service.ICustomerService;
import com.demo.mcctiendademo.Util.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${version.api}/customer")
@RequiredArgsConstructor
public class CustomerController implements IController<CustomerCreateRequest>{

    private final ICustomerService customerService;

    @PostMapping("/save")
    @Override
    public ResponseEntity<SuccessMessage<CustomerCreateResponse>> saveEndPoint(@RequestBody CustomerCreateRequest data) throws Exception {
        CustomerCreateResponse response = customerService.save(data);
        SuccessMessage message = SuccessMessage.success(HttpStatus.CREATED,"recurse create",response);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/saveAll")
    @Override
    public ResponseEntity<SuccessMessage<List<CustomerCreateResponse>>> saveAllEndPoint(@RequestBody List<CustomerCreateRequest> listData) throws Exception {
        List<CustomerCreateResponse> response = customerService.saveAll(listData);
        SuccessMessage message = SuccessMessage.success(HttpStatus.OK,"recurse saveAll",response);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<SuccessMessage<List<CustomerCreateResponse>>> getAllEndPoint() throws Exception {
        List<CustomerCreateResponse>customerCreateResponses = customerService.findAll();
        SuccessMessage message = SuccessMessage.success(HttpStatus.OK,"recurse getAll",customerCreateResponses);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<SuccessMessage<CustomerCreateResponse>> getByIdEndPoint(@PathVariable UUID id) throws Exception {
        CustomerCreateResponse response = customerService.findById(id);
        SuccessMessage message = SuccessMessage.success(HttpStatus.OK,"recurse getById",response);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<SuccessMessage<Void>> deleteEndPoint(@PathVariable UUID id) throws Exception {
        customerService.delete(id);
        SuccessMessage message = SuccessMessage.success(HttpStatus.OK,"recurse deleteById",null);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
