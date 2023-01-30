package com.qqri.userservice.client.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {
    private String productId;
    private Integer totalPrice;
    private Date createdAt;
    private String sellerId;
    private String buyerId;
    private String orderId;
}
