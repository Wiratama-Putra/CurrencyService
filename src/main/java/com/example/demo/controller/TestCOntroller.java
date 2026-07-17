package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCOntroller {

    @GetMapping
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/test")
    public String helloWorld(
            @RequestParam(value = "string") String str){
        return str;
    }

}
