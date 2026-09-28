package com.tradex.orderservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradex.orderservice.dto.OrderDTO;
import com.tradex.orderservice.entity.OrderEntity;
import com.tradex.orderservice.exception.OrderAlreadyExistsException;
import com.tradex.orderservice.mapper.OrderManagementMapper;
import com.tradex.orderservice.repository.OrderManagementRepository;
import com.tradex.orderservice.service.OrderManagementService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class OrderManagementServiceImpl implements OrderManagementService {

    private final OrderManagementMapper orderManagementMapper;

    private final OrderManagementRepository orderManagementRepository;

    @Override
    @Transactional(transactionManager = "transactionManager")
    public OrderDTO saveOrderCreateRequest(OrderDTO dto) {

        log.info("Saving order. eventId={}, symbol={}", dto.getEventId(), dto.getSymbol());
        if (dto.getEventId() != null && !dto.getEventId().isBlank()) {
            Optional<OrderEntity> existingOrder = orderManagementRepository.findByEventId(dto.getEventId());
            if (existingOrder.isPresent()) {
                throw new OrderAlreadyExistsException("Order already exists for eventId: " + dto.getEventId());
            }
        }
        OrderEntity entity = orderManagementMapper.mapTo(dto, OrderEntity.class);
        entity = orderManagementRepository.save(entity);
        log.info("Order saved successfully. orderId={}, eventId={}", entity.getOrderId(), entity.getEventId());
        return orderManagementMapper.mapTo(entity, OrderDTO.class);
    }

}