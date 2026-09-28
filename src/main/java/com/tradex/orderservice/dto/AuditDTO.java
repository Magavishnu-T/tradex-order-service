package com.tradex.orderservice.dto;

import java.time.Instant;

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
public abstract class AuditDTO {

    private Instant createdOn;

    private String createdBy;

    private Instant updatedOn;

    private String updatedBy;
}
