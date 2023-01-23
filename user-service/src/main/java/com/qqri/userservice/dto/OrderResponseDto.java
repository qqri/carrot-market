package com.qqri.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {
    private String productId;
    private Integer unitPrice;
    private Integer totalPrice;
    private Integer qty;
    private Date createdAt;

    private String orderId;
}
