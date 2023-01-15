package com.qqri.website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @GetMapping("/hello")
    public String index() {
        return "hello world";
    }
}
