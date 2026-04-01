package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import com.example.demo.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //exception tự định nghĩa
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingAppException() {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ErrorCode.UNCATEGORIZED_ERROR.getMessage())
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }

    //các exception chưa được định nghĩa
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ErrorCode.UNCATEGORIZED_ERROR.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
