package com.tradex.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tradex.orderservice.entity.OrderEntity;

public interface OrderManagementRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByEventId(String eventId);

}
