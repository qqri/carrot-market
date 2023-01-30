package com.qqri.userservice.service;

import com.qqri.userservice.client.OrderServiceClient;
import com.qqri.userservice.config.jwt.JwtTokenProvider;
import com.qqri.userservice.domain.UserRepository;
import com.qqri.userservice.domain.Users;
import com.qqri.userservice.client.dto.OrderResponseDto;
import com.qqri.userservice.dto.UserResponseDto;
import com.qqri.userservice.dto.UserReviewScoreRequestDto;
import com.qqri.userservice.dto.UserSaveRequestDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
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

    public UserResponseDto getUsersSellHistory(String name) {
        Users userEntity = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
        UserResponseDto userResponseDto = new UserResponseDto(userEntity);

        /*Feign client except handler
        * 주문 정보 누락 시에 500 에러가 아닌 누락된 채로 정보 나오록
        * */
        List<OrderResponseDto> orderList = null;
        try{
            orderList = orderServiceClient.getAllSellerOrder(name);
        }catch (FeignException ex) {
            log.error(ex.getMessage());
        }
        userResponseDto.setSellList(orderList);
        return userResponseDto;
    }
    public UserResponseDto getUsersBuyHistory(String name) {
        Users userEntity = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID 입니다."));
        UserResponseDto userResponseDto = new UserResponseDto(userEntity);

        List<OrderResponseDto> orderList = null;
        try{
            orderList = orderServiceClient.getAllBuyerOrder(name);
        }catch (FeignException ex) {
            log.error(ex.getMessage());
        }

        userResponseDto.setBuyList(orderList);
        return userResponseDto;
    }
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUserByAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }


    public String setUserTemperatureByScore(UserReviewScoreRequestDto requestDto) {
        int score = requestDto.getScore();
        Users users = userRepository.findByName(requestDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지않은 ID 입니다."));
        users.updateTemp(score);
        return requestDto.getName();
    }
}
