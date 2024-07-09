package com.example.demo.calculator;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

enum CalculatorState {
    INIT,
    FIRST_NUM,
    SECOND_NUM,
    ERROR
}

@Service
public class CalculatorService {

    private String resultString = "";
    private float result = 0;
    private float num1 = 0;
    private float num2 = 0;
    private String op = "";
    private CalculatorState currentState = CalculatorState.INIT;
    private ArrayList<String> prevResults = new ArrayList<String>();

    public float calculate(float num1, float num2, String op) {
        float result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;

            case "*":
                result = num1 * num2;
                break;

            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);

        }
        this.prevResults.add( String.valueOf(num1) + op + String.valueOf(num2) + "=" + String.valueOf(result));
        return result;
    }

    public String process(String eval) {
        // move through the string
        this.clearCalculator();
        for (int i = 0; i < eval.length(); i++) {
            char c = eval.charAt(i);
            // if c is number call processNumber
            if (Character.isDigit(c)) {
                // cast c to number
                processNumber(Character.getNumericValue(c));
            }
            // if c is not a number call processSymbol
            else {
                processSymbol(c);
            }
        }
        return this.resultString;
    }

    private void clearCalculator() {
        this.resultString = "";
        this.result = 0;
        this.num1 = 0;
        this.num2 = 0;
        this.op = "";
        this.currentState = CalculatorState.INIT;
    }

    private void processNumber(int number) {
        switch (this.currentState) {
            case INIT:
                this.num1 = number;
                this.currentState = CalculatorState.FIRST_NUM;
                break;
            case FIRST_NUM:
                this.num1 = this.num1 * 10 + number;
                break;
            case SECOND_NUM:
                this.num2 = this.num2 * 10 + number;
                break;
            case ERROR:
                this.resultString = "Error";
                break;
        
            default:
                break;
        }
    }

    private void processSymbol(char symbol) {
        switch (this.currentState) {
            case INIT:
                this.currentState = CalculatorState.ERROR;
                break;
            case FIRST_NUM:
                if (symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/') {
                    this.op = String.valueOf(symbol);
                    this.currentState = CalculatorState.SECOND_NUM;
                } else {
                    this.currentState = CalculatorState.ERROR;
                }
                break;
            case SECOND_NUM:
                if (symbol == '=') {
                    this.result = calculate(this.num1, this.num2, this.op);
                    this.resultString = String.valueOf(this.num1) + this.op + String.valueOf(this.num2) + "=" + String.valueOf(this.result);
                    this.currentState = CalculatorState.INIT;
                } else {
                    this.currentState = CalculatorState.ERROR;
                }
                break;
            case ERROR:
                this.resultString = "Error";
                break;
        
            default:
                break;
        }
    }

    public ArrayList<String> getPrevResults() {
        return prevResults;
    }
}
