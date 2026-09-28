package com.tradex.orderservice.service;

import com.tradex.orderservice.dto.OrderDTO;

public interface OrderEventService {

    public void publishOrderCreateEvent(OrderDTO dto);
}
