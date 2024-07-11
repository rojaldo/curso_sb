package com.example.demo.errors;

import com.example.demo.library.books.IBookResponse;
import com.example.demo.library.user.IUserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto implements IUserResponse, IBookResponse {
    private int code;
    private String message;
}
