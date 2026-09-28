package com.tradex.orderservice.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.tradex.orderservice.client.api.OrdersApi;
import com.tradex.orderservice.client.model.OrderCreateRequest;
import com.tradex.orderservice.client.model.OrderCreateResponse;
import com.tradex.orderservice.dto.OrderDTO;
import com.tradex.orderservice.mapper.OrderManagementMapper;
import com.tradex.orderservice.service.OrderEventService;
import com.tradex.orderservice.service.OrderManagementService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class OrderManagementController implements OrdersApi {

    private final OrderManagementService orderManagementService;

    private final OrderManagementMapper orderManagementMapper;

    private final OrderEventService orderEventService;

    @Override
    public ResponseEntity<OrderCreateResponse> createOrder(OrderCreateRequest orderCreateRequest) {
        log.info("Received order creation request for symbol: {}", orderCreateRequest.getSymbol());
        OrderDTO dto = orderManagementMapper.mapCreateOrderToDto(orderCreateRequest);
        dto = orderManagementService.saveOrderCreateRequest(dto);
        orderEventService.publishOrderCreateEvent(dto);
        OrderCreateResponse response = orderManagementMapper.mapDtoToCreateOrderResponse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> cancelOrder(UUID orderId) {
        // TODO Auto-generated method stub
        return OrdersApi.super.cancelOrder(orderId);
    }

    @Override
    public ResponseEntity<OrderCreateResponse> getOrder(UUID orderId) {
        // TODO Auto-generated method stub
        return OrdersApi.super.getOrder(orderId);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        // TODO Auto-generated method stub
        return OrdersApi.super.getRequest();
    }

}