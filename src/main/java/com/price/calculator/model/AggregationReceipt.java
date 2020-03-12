package com.price.calculator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode(of = {"description", "aggregation"})
@RequiredArgsConstructor(staticName = "of")
public class AggregationReceipt {
    private static final String TOTAL = "Total:";
    private static final String SUBTOTAL = "Subtotal:";
    private final String description;
    private final BigDecimal aggregation;

    public static AggregationReceipt total(BigDecimal price) {
        return of(TOTAL, price);
    }

    public static AggregationReceipt subTotal(BigDecimal price) {
        return of(SUBTOTAL, price);
    }
}
