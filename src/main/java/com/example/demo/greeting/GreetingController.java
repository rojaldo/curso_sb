package com.example.demo.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String getMethodName(
        @RequestParam(name="msg", required=false, defaultValue="World") String message, 
        Model view) {
        message = "Hello " + message;
        view.addAttribute("my_msg", message);
        return "template";
    } 
    
}
