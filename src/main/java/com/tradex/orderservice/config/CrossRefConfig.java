package com.tradex.orderservice.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tradex.order.event.OrderSide;
import com.tradex.order.event.OrderStatus;
import com.tradex.order.event.OrderType;
import com.tradex.orderservice.client.model.OrderCreateRequest.OrderTypeEnum;
import com.tradex.orderservice.client.model.OrderCreateRequest.SideEnum;
import com.tradex.orderservice.client.model.OrderCreateResponse;

@Configuration
public class CrossRefConfig {

    @Bean("mapOrderTypeRequest")
    public Map<OrderTypeEnum, String> mapOrderTypeRequest() {
        return Map.of(
                OrderTypeEnum.MARKET, "Market",
                OrderTypeEnum.LIMIT, "Limit");
    }

    @Bean("mapSideRequest")
    public Map<SideEnum, String> mapSideRequest() {
        return Map.of(
                SideEnum.BUY, "Buy",
                SideEnum.SELL, "Sell");
    }

    @Bean("mapOrderTypeResponse")
    public Map<String, OrderCreateResponse.OrderTypeEnum> mapOrderTypeResponse() {
        return Map.of(
                "Market", OrderCreateResponse.OrderTypeEnum.MARKET,
                "Limit", OrderCreateResponse.OrderTypeEnum.LIMIT);
    }

    @Bean("mapSideResponse")
    public Map<String, OrderCreateResponse.SideEnum> mapSideResponse() {
        return Map.of(
                "Buy", OrderCreateResponse.SideEnum.BUY,
                "Sell", OrderCreateResponse.SideEnum.SELL);
    }

    @Bean("mapOrderTypeProducer")
    public Map<String, OrderType> mapOrderTypeProducer() {
        return Map.of(
                "Market", OrderType.MARKET,
                "Limit", OrderType.LIMIT);
    }

    @Bean("mapOrderSideProducer")
    public Map<String, OrderSide> mapOrderSideProducer() {
        return Map.of(
                "Buy", OrderSide.BUY,
                "Sell", OrderSide.SELL);
    }

    @Bean("mapOrderStatusProducer")
    public Map<String, OrderStatus> mapOrderStatusProducer() {
        return Map.of(
                "Cancelled", OrderStatus.CANCELLED,
                "Successful", OrderStatus.SUCCESSFUL,
                "Pending", OrderStatus.PENDING,
                "Rejected", OrderStatus.REJECTED);
    }
}