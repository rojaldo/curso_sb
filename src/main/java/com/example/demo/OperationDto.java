package com.example.demo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class OperationDto {

    @Min(-1000)
    @Max(1000)
    private float num1 = 2;

    @Min(-1000)
    @Max(1000)
    private float num2 = 3;

    @Pattern(regexp = "[+\\-*/]")
    private String op = "+";
    
    public float getNum1() {
        return num1;
    }
    
    public void setNum1(float num1) {
        this.num1 = num1;
    }
    
    public float getNum2() {
        return num2;
    }
    
    public void setNum2(float num2) {
        this.num2 = num2;
    }
    
    public String getOp() {
        return op;
    }
    
    public void setOp(String op) {
        this.op = op;
    }
}
