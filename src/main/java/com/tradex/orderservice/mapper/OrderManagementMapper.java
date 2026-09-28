package com.tradex.orderservice.mapper;

import static com.tradex.orderservice.constant.GlobalConstant.ORDER_MANAGEMENT_SYSTEM;
import static com.tradex.orderservice.utils.BigDecimalUtils.toNullable;
import static com.tradex.orderservice.utils.CommonUtils.generateEventId;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tradex.order.event.OrderCreated;
import com.tradex.order.event.OrderSide;
import com.tradex.order.event.OrderStatus;
import com.tradex.order.event.OrderType;
import com.tradex.orderservice.client.model.OrderCreateRequest;
import com.tradex.orderservice.client.model.OrderCreateRequest.OrderTypeEnum;
import com.tradex.orderservice.client.model.OrderCreateRequest.SideEnum;
import com.tradex.orderservice.client.model.OrderCreateResponse;
import com.tradex.orderservice.constant.DBConstant;
import com.tradex.orderservice.dto.OrderDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderManagementMapper {

    private final Map<OrderTypeEnum, String> mapOrderTypeRequest;

    private final Map<SideEnum, String> mapSideRequest;

    private final Map<String, OrderCreateResponse.SideEnum> mapSideResponse;

    private final Map<String, OrderCreateResponse.OrderTypeEnum> mapOrderTypeResponse;

    private final Map<String, OrderType> mapOrderTypeProducer;

    private final Map<String, OrderSide> mapOrderSideProducer;

    private final Map<String, OrderStatus> mapOrderStatusProducer;

    private final ModelMapper modelMapper;

    public <T> T mapTo(Object source, Class<T> targetType) {
        return modelMapper.map(source, targetType);
    }

    public OrderDTO mapCreateOrderToDto(OrderCreateRequest orderCreateRequest) {
        OrderDTO dto = new OrderDTO();
        dto.setQuantity(orderCreateRequest.getQuantity());
        dto.setSide(mapSideRequest.get(orderCreateRequest.getSide()));
        dto.setPrice(toNullable(orderCreateRequest.getPrice()));
        dto.setOrderType(mapOrderTypeRequest.get(orderCreateRequest.getOrderType()));
        dto.setSymbol(orderCreateRequest.getSymbol());
        dto.setStatus(DBConstant.PENDING);
        dto.setEventId(generateEventId());
        dto.setCreatedBy(ORDER_MANAGEMENT_SYSTEM);
        dto.setUpdatedBy(ORDER_MANAGEMENT_SYSTEM);
        return dto;
    }

    public OrderCreated mapDtoToOrderCreated(OrderDTO dto) {
        return OrderCreated.newBuilder()
                .setOrderId(String.valueOf(dto.getOrderId()))
                .setEventId(dto.getEventId())
                .setOrderType(mapOrderTypeProducer.get(dto.getOrderType()))
                .setPrice(dto.getPrice())
                .setSide(mapOrderSideProducer.get(dto.getSide()))
                .setStatus(mapOrderStatusProducer.get(dto.getStatus()))
                .setQuantity(dto.getQuantity())
                .setSymbol(dto.getSymbol())
                .setCreatedAt(dto.getCreatedOn())
                .build();
    }

    public OrderCreateResponse mapDtoToCreateOrderResponse(OrderDTO dto) {
        OrderCreateResponse response = new OrderCreateResponse();
        response.setQuantity(dto.getQuantity());
        response.setSide(mapSideResponse.get(dto.getSide()));
        response.setPrice(toNullable(dto.getPrice()));
        response.setOrderType(mapOrderTypeResponse.get(dto.getOrderType()));
        response.setSymbol(dto.getSymbol());
        response.setEventId(dto.getEventId());
        return response;
    }
}