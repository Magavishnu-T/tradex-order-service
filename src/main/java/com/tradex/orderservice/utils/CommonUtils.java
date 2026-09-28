package com.tradex.orderservice.utils;

import static com.tradex.orderservice.constant.GlobalConstant.EVENT_ID_FORMATTER;
import static com.tradex.orderservice.constant.GlobalConstant.ORDER_CREATE_EVENT_PATTERN;

import java.time.LocalDateTime;
import java.util.UUID;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static String generateEventId() {
        String timestamp = LocalDateTime.now().format(EVENT_ID_FORMATTER);
        return ORDER_CREATE_EVENT_PATTERN + "-" + timestamp + "-" + UUID.randomUUID();
    }
}
