package com.qqri.orderservice.service;

import com.qqri.orderservice.domain.orders.Orders;
import com.qqri.orderservice.domain.orders.OrdersRepository;
import com.qqri.orderservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrdersRepository ordersRepository;

    public String createOrder(Long userId, OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());
        orderDto.setUserId(userId);
        return ordersRepository.save(orderDto.toEntity()).getOrderId();
    }

    public OrderDto findByOrderId(String orderId) {
        Orders entity = ordersRepository.findByOrderId(orderId);

        return new OrderDto(entity);
    }

    public List<Orders> findByUserId(Long userId) {
        return ordersRepository.findByUserId(userId);
    }
}
