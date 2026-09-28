package com.tradex.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends AuditDTO {

    private long orderId;

    private String symbol;

    private Integer quantity;

    private String side;

    private String orderType;

    private BigDecimal price;

    private String status;

    private String eventId;

}