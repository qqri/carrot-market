package com.qqri.orderservice.controller;


import com.qqri.orderservice.domain.orders.Orders;
import com.qqri.orderservice.dto.OrderDto;
import com.qqri.orderservice.dto.OrderRequestDto;
import com.qqri.orderservice.dto.OrderResponseDto;
import com.qqri.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final Environment env;
    private final OrderService orderService;

    @PostMapping("/orders/{userId}")
    public String createUser(@PathVariable Long userId,
                                                       @RequestBody OrderRequestDto requestDto) {
        OrderDto orderDto = new OrderDto(requestDto);
        return orderService.createOrder(userId, orderDto);
    }

    @GetMapping("/orders/order/{orderId}")
    public OrderDto getOneOrder(@PathVariable String orderId) {
        return orderService.findByOrderId(orderId);
    }

    @GetMapping("/orders/seller/{sellerId}")
    public ResponseEntity<List<Orders>> getAllSellerOrder(@PathVariable String sellerId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findBySellerId(sellerId));
    }

    @GetMapping("/orders/buyer/{buyerId}")
    public ResponseEntity<List<Orders>> getAllBuyerOrder(@PathVariable String buyerId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByBuyerId(buyerId));
    }

}
