package com.qqri.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qqri.userservice.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String name;

    private List<OrderResponseDto> orders;

    public UserResponseDto(Users entity) {
        this.name = entity.getName();
        this.orders = new ArrayList<OrderResponseDto>();
    }
}
