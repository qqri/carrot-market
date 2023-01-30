package com.qqri.userservice.controller;
import com.qqri.userservice.domain.Users;
import com.qqri.userservice.dto.UserResponseDto;
import com.qqri.userservice.dto.UserReviewScoreRequestDto;
import com.qqri.userservice.dto.UserSaveRequestDto;
import com.qqri.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;


@RestController
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

    @GetMapping("/sell/{sellerId}")
    public ResponseEntity<UserResponseDto> getUsersSellHistory(@PathVariable String sellerId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersSellHistory(sellerId));
    }

    @GetMapping("/buy/{buyerId}")
    public ResponseEntity<UserResponseDto> getUsersBuyHistory(@PathVariable String buyerId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersSellHistory(buyerId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByAll());
    }

    @GetMapping("/review/")
    public String reviewUserByScore(UserReviewScoreRequestDto requestDto) {
        return userService.setUserTemperatureByScore(requestDto);
    }
}

