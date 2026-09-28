package com.tradex.orderservice.constant;

import java.time.format.DateTimeFormatter;

public class GlobalConstant {

    public static final String ORDER_MANAGEMENT_SYSTEM = "ORDER MANAGEMENT SYSTEM";

    public static final DateTimeFormatter EVENT_ID_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public static final String ORDER_CREATE_EVENT_PATTERN = "EOC";

}
