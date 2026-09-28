package com.tradex.orderservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OrderEntity extends AuditEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "symbol", length = 50)
    private String symbol;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "side", length = 10)
    private String side;

    @Column(name = "order_type", length = 20)
    private String orderType;

    @Column(name = "price", precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "event_id", length = 255)
    private String eventId;

}