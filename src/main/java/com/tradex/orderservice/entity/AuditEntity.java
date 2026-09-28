package com.tradex.orderservice.entity;

import java.time.Instant;

import org.hibernate.annotations.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Audited
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @CreatedDate
    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_on")
    private Instant updatedOn;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;
}
