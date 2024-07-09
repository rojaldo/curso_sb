package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    
    public float calculate(float num1, float num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
                
            case "-":
                return num1 - num2;
                
            case "*":
                return num1 * num2;
                
            case "/":
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
                
        }
    }
}
