package com.qqri.userservice.dto;

import com.qqri.userservice.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    @Setter
    private String password;

    @Builder
    public UserSaveRequestDto(String name , String password) {
        this.name = name;
        this.password = password;
    }

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .password(password)
                .temperature(36.5) //처음엔 36.5
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }
}