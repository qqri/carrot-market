package com.qqri.userservice.controller;

import com.qqri.userservice.config.jwt.JwtTokenProvider;
import com.qqri.userservice.domain.Users;
import com.qqri.userservice.domain.UserRepository;
import com.qqri.userservice.dto.UserSaveRequestDto;
import com.qqri.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/hello")
    public String user() {
        return "hi user-service~!! ";
    }


    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.join(userSaveRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserSaveRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @GetMapping("/test")
    public String test() {
        return "test success";
    }
}
