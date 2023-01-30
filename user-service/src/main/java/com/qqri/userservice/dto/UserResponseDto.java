package com.qqri.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qqri.userservice.client.dto.OrderResponseDto;
import com.qqri.userservice.domain.users.Users;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String name;

    private List<OrderResponseDto> sellList;
    private List<OrderResponseDto> buyList;

    public UserResponseDto(Users entity) {
        this.name = entity.getName();
        this.sellList = new ArrayList<OrderResponseDto>();
        this.buyList = new ArrayList<OrderResponseDto>();
    }
}
