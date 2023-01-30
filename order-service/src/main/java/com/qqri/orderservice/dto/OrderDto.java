package com.qqri.orderservice.dto;

import com.qqri.orderservice.domain.orders.Orders;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Data
public class OrderDto {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private Long userId;
    @Setter
    private List<Orders> orderList;
    public OrderDto(Orders entity) {
        this.orderId = entity.getOrderId();
        this.productId = entity.getProductId();
        this.totalPrice = entity.getTotalPrice();
    }

    public OrderDto(OrderRequestDto requestDto) {
        this.productId = requestDto.getProductId();
        this.qty = requestDto.getQty();
        this.unitPrice = requestDto.getUnitPrice();
    }

    @Builder
    public OrderDto(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String orderId, Long userId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.userId = userId;
    }

    public Orders toEntity(){
        return Orders.builder()
                .productId(productId)
                .orderId(orderId)
                .totalPrice(totalPrice)
                .build();
    }

}
