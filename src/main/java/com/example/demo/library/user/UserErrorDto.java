package com.example.demo.library.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserErrorDto implements IUserResponse {
    private int code;
    private String message;
}
