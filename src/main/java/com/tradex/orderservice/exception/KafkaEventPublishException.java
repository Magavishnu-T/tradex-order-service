package com.tradex.orderservice.exception;

public class KafkaEventPublishException extends RuntimeException {

    public KafkaEventPublishException(String message, Throwable cause) {
        super(message, cause);
    }
}