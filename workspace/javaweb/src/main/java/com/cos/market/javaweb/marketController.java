package com.cos.market.javaweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class marketController {

    @GetMapping("/home")
    public String hello() {
        return "Hello World!";
    }
    
}