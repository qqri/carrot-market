package com.qqri.userservice.service;

import com.qqri.userservice.config.jwt.JwtTokenProvider;
import com.qqri.userservice.domain.UserRepository;
import com.qqri.userservice.domain.Users;
import com.qqri.userservice.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public String join(UserSaveRequestDto userSaveRequestDto) {
        userSaveRequestDto.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        userRepository.save(userSaveRequestDto.toEntity());
        return "회원가입 완료";
    }

    public String login(UserSaveRequestDto requestDto) {
        Users member = userRepository.findByName(requestDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());

    }
}
