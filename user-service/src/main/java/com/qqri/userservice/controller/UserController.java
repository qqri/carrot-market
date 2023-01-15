package com.qqri.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
public class UserController {
    @GetMapping("/hello")
    public String user() {
        return "hi user-service~!! ";
    }
}
