package com.qqri.userservice.client;

import com.qqri.userservice.dto.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/* 사용할 Service name 으로 등록해줍니다. */
@FeignClient(name="order-service", url = "http://localhost:8000")
public interface OrderServiceClient {

    /* getOrders 를 만들어주고 해당경로값을 Mapping 한다.
     * userId는 가변값이기 때문에 PathVariable 으로 등록.
     */
    @GetMapping("order-service/orders/user/{userId}")
    List<OrderResponseDto> getOrders(@PathVariable Long userId);


}