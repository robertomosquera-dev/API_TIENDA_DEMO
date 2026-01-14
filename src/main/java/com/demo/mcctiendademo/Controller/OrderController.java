package com.demo.mcctiendademo.Controller;

import com.demo.mcctiendademo.Http.Request.OrderCreateRequest;
import com.demo.mcctiendademo.Http.Response.OrderFind;
import com.demo.mcctiendademo.Http.Response.OrderItemCreateResponse;
import com.demo.mcctiendademo.Service.IOrderProcessService;
import com.demo.mcctiendademo.Service.IPurchaseOrderService;
import com.demo.mcctiendademo.Util.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${version.api}/order")
@RequiredArgsConstructor
public class OrderController implements IController<OrderCreateRequest>{

    private final IPurchaseOrderService purchaseOrderService;
    private final IOrderProcessService processService;

    @PostMapping("/save")
    @Override
    public ResponseEntity<?> saveEndPoint(@RequestBody OrderCreateRequest data) throws Exception {
        OrderFind orderFind = processService.processOrder(data);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.CREATED,"recurse create",orderFind);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    @Override
    public ResponseEntity<?> saveAllEndPoint(List<OrderCreateRequest> listData) throws Exception {
        return null;
    }

    @GetMapping("/getAll")
    @Override
    public ResponseEntity<?> getAllEndPoint() throws Exception {
        List<OrderFind> orderFinds = processService.findAll();
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.OK,"message success",orderFinds);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }
    @GetMapping("/{id}/items")
    public ResponseEntity<?> getItemsByOrderId(@PathVariable UUID id) throws Exception {
        List<OrderItemCreateResponse>orderItemCreateResponses = processService.findItemsByOrderId(id);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.OK,"message success",orderItemCreateResponses);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> getByIdEndPoint(@PathVariable UUID id) throws Exception {
        OrderFind orderFind = processService.findById(id);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.OK,"recurse getById",orderFind);
        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteEndPoint(@PathVariable UUID id) throws Exception {
        purchaseOrderService.delete(id);
        SuccessMessage successMessage = SuccessMessage.success(HttpStatus.NO_CONTENT,"recurse delete",null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
    }
}
