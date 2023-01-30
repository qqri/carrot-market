package com.qqri.orderservice.domain.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    Orders findByOrderId(String productId);
    List<Orders> findByUserId(Long Id);
    List<Orders> findBySellerId(String sellerId);
    List<Orders> findByBuyerId(String buyerId);
}
