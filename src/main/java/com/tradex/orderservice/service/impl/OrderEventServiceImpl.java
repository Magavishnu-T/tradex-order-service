package com.tradex.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tradex.order.event.OrderCreated;
import com.tradex.orderservice.dto.OrderDTO;
import com.tradex.orderservice.kafka.EventProducer;
import com.tradex.orderservice.mapper.OrderManagementMapper;
import com.tradex.orderservice.service.OrderEventService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderEventServiceImpl implements OrderEventService {

    @Value("${kafka.orderCreateTopic}")
    private String orderCreateTopic;

    private final OrderManagementMapper orderManagementMapper;

    private final EventProducer eventProducer;

    public OrderEventServiceImpl(OrderManagementMapper orderManagementMapper, EventProducer eventProducer) {
        this.orderManagementMapper = orderManagementMapper;
        this.eventProducer = eventProducer;
    }

    @Override
    public void publishOrderCreateEvent(OrderDTO dto) {
        log.info("Creating OrderCreated event. orderId={}, eventId={}", dto.getOrderId(), dto.getEventId());
        OrderCreated orderCreated = orderManagementMapper.mapDtoToOrderCreated(dto);
        eventProducer.publishEvent(orderCreateTopic, dto.getEventId(), orderCreated);
        log.info("OrderCreated event published. orderId={}, eventId={}", dto.getOrderId(), dto.getEventId());
    }
}