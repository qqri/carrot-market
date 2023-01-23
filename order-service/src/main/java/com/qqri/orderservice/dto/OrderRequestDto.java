package com.qqri.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}
