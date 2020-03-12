package com.price.calculator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor(staticName = "discount")
public class Discount {
    private final String description;
    private final BigDecimal amount;

    public static Discount empty() {
        return discount("", ZERO);
    }
}
