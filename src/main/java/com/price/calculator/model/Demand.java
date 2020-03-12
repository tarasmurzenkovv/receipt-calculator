package com.price.calculator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Demand {
    private final Product product;
    private final double amount;
    private final BigDecimal price;

    public static Demand demand(Product product, double amount) {
        return new Demand(product, amount, null);
    }
}
