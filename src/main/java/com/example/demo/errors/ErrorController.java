package com.example.demo.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().body(ErrorDto.builder().code(400).message(e.getMessage()).build());
        }else if (e instanceof NoResourceFoundException) {
            return ResponseEntity.status(404).body(ErrorDto.builder().code(404).message(e.getMessage()).build());
        }else if (e instanceof NullPointerException) {
            return ResponseEntity.status(400).body(ErrorDto.builder().code(400).message(e.getMessage()).build());
        }else if (e instanceof IllegalStateException) {
            return ResponseEntity.status(400).body(ErrorDto.builder().code(400).message(e.getMessage()).build());
        }
        return ResponseEntity.status(500).body(ErrorDto.builder().code(500).message(e.getMessage()).build());
    }
    
}
