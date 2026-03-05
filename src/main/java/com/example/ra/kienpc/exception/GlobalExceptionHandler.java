package com.example.ra.kienpc.exception;

import com.example.ra.kienpc.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)//Bắt exception validation
    @ResponseStatus(HttpStatus.BAD_REQUEST)//Set HTTP status = 400
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        //Lấy danh sách lỗi validation
        //Duyệt từng lỗi ( forEach )
        ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        //Trả về response format chuẩn
        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>("FAIL","Validation Failed",errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleDuplicateResourceException(DuplicateResourceException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>("FAIL","Validation Failed",errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT); // 409
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>("FAIL","Validation Failed",errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleException(Exception ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("ERROR",ex.getMessage());
        ApiResponse<Map<String, String>> apiResponse = new ApiResponse<>("FAIL","INTERNAL SERVER ERROR",errors);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
