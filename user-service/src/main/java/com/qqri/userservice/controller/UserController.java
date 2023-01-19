package com.qqri.userservice.controller;
import com.qqri.userservice.dto.UserSaveRequestDto;
import com.qqri.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/hello")
    public String user() {
        return "hi user-service~!! ";
    }

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
