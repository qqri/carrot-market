package com.qqri.userservice.service;

import com.qqri.userservice.client.OrderServiceClient;
import com.qqri.userservice.config.jwt.JwtTokenProvider;
import com.qqri.userservice.domain.UserRepository;
import com.qqri.userservice.domain.Users;
import com.qqri.userservice.dto.OrderResponseDto;
import com.qqri.userservice.dto.UserResponseDto;
import com.qqri.userservice.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final OrderServiceClient orderServiceClient;
    public String join(UserSaveRequestDto userSaveRequestDto) {
        userSaveRequestDto.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        userRepository.save(userSaveRequestDto.toEntity());
        return "회원가입 완료";
    }

    public String login(UserSaveRequestDto requestDto) {
        Users member = userRepository.findByName(requestDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 NAME 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

    }

    public UserResponseDto getUserByUserId(Long id) {
        Users userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
        UserResponseDto userResponseDto = new UserResponseDto(userEntity);
        List<OrderResponseDto> orderList = orderServiceClient.getOrders(id);
        userResponseDto.setOrders(orderList);
        return userResponseDto;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getUserByAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }





}
