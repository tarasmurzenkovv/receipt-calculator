package com.price.calculator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Receipt {
    private AggregationReceipt subTotal;
    private AggregationReceipt total;
    private List<Discount> discounts;
}
