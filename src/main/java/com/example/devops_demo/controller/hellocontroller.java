package com.example.devops_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellocontroller {

    @GetMapping("/")
    public String hello() {
        return "DevOps Project Built successfully!";
    }
}
