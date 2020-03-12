package com.price.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Product {
    SOUP(BigDecimal.valueOf(0.65)),
    BREAD(BigDecimal.valueOf(0.8)),
    MILK(BigDecimal.valueOf(1.3)),
    APPLE(BigDecimal.valueOf(1));
    private final BigDecimal price;

    public static Product from(String name) {
        return Arrays.stream(Product.values())
                .filter(product -> product.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find product"));
    }
}
