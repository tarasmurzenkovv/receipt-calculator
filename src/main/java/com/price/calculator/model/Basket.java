package com.price.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor(staticName = "basket")
public class Basket {
    private final List<Discount> discounts;
    private final List<Product> products;
}
