package com.example.demo.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class CalculatorRestController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public ResponseEntity<Map> getMethodName(@Validated OperationDto operationDto) {
        float result = this.calculatorService.calculate(operationDto.getNum1(), operationDto.getNum2(), operationDto.getOp());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("num_1", operationDto.getNum1(), "num_2", operationDto.getNum2(), "op", operationDto.getOp(), "result", result, "prev_results", this.calculatorService.getPrevDtoResults()));
    }

    @GetMapping("/eval")
    public ResponseEntity<Map> getMethodName(@RequestParam(name = "eval", required = true, defaultValue = "2+3=") String eval) {
        String resultEval = this.calculatorService.process(eval);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("eval", eval,"result_eval", resultEval, "prev_results", this.calculatorService.getPrevDtoResults()));
    }
    
    
}
