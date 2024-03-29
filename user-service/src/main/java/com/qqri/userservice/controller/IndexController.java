package com.qqri.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private Environment env;

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }
}
