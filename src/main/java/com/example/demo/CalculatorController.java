package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String calculator(
            @RequestParam(name = "num1", required = false, defaultValue = "2") float num1,
            @RequestParam(name = "num2", required = false, defaultValue = "3") float num2,
            @RequestParam(name = "op", required = false, defaultValue = "+") String op,
            Model view) {
                float result = this.calculatorService.calculate(num1, num2, op);
                view.addAttribute("result", result);
                view.addAttribute("num1", num1);
                view.addAttribute("num2", num2);
                view.addAttribute("op", op);
        return "calculator";
    }

    @GetMapping("/calc")
    public String calculator(@Validated OperationDto operationDto, Model view) {
                float result = this.calculatorService.calculate(operationDto.getNum1(), operationDto.getNum2(), operationDto.getOp());
                view.addAttribute("result", result);
                view.addAttribute("num1", operationDto.getNum1());
                view.addAttribute("num2", operationDto.getNum2());
                view.addAttribute("op", operationDto.getOp());
        return "calculator";
    }


}
