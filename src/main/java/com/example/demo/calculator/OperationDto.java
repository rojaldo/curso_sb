package com.example.demo.calculator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationDto {

    @Min(-1000)
    @Max(1000)
    private float num1;

    @Min(-1000)
    @Max(1000)
    private float num2;

    @Pattern(regexp = "[+\\-*/]")
    private String op;

    private float result;

}
