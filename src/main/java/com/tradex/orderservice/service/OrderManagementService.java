package com.tradex.orderservice.service;

import com.tradex.orderservice.dto.OrderDTO;

public interface OrderManagementService {

    public OrderDTO saveOrderCreateRequest(OrderDTO dto);

}
