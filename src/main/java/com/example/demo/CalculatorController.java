package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String calculator(
            @RequestParam(name = "eval", required = false, defaultValue = "2+3=") String eval, Model view) {
                String resultEval = this.calculatorService.process(eval);
                log.info("resultEval: " + resultEval);
                view.addAttribute("resultEval", resultEval);
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
