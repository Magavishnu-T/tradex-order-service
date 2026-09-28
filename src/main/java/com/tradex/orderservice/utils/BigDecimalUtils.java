package com.tradex.orderservice.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static BigDecimal toNullable(BigDecimal value) {
        return value != null ? value : null;
    }
}
