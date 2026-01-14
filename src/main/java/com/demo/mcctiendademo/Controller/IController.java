package com.demo.mcctiendademo.Controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IController <T>{
    ResponseEntity<?> saveEndPoint(T data) throws  Exception;
    ResponseEntity<?> saveAllEndPoint(List<T>listData) throws  Exception;
    ResponseEntity<?> getAllEndPoint() throws  Exception;
    ResponseEntity<?> getByIdEndPoint(UUID id) throws  Exception;
    ResponseEntity<?> deleteEndPoint(UUID id) throws  Exception;
}
