package com.price.calculator.service;

import java.math.BigDecimal;

import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.UK;

public interface MoneyUtils {
    static String format(BigDecimal bigDecimal) {
        return getCurrencyInstance(UK).format(bigDecimal);
    }
}
