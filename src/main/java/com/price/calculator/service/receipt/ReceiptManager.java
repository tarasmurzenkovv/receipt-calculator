package com.price.calculator.service.receipt;

import com.price.calculator.model.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static com.price.calculator.model.AggregationReceipt.subTotal;
import static com.price.calculator.model.AggregationReceipt.total;
import static com.price.calculator.service.CollectionUtils.isEmpty;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

public class ReceiptManager {
    public Receipt generate(Basket basket) {
        final var discounts = aggregateDiscounts(basket.getDiscounts());
        final var subtotal = countSubtotal(basket.getProducts());
        final var total = countTotal(subtotal, countTotalDiscount(discounts));
        return Receipt.builder()
                .discounts(discounts)
                .subTotal(subtotal)
                .total(total)
                .build();
    }

    private static AggregationReceipt countSubtotal(Collection<Product> products) {
        final var totalPrice = products
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(ZERO);
        return subTotal(totalPrice);
    }

    private static List<Discount> aggregateDiscounts(List<Discount> discounts) {
        if (isEmpty(discounts)) {
            return emptyList();
        }
        return discounts.stream()
                .collect(groupingBy(Discount::getDescription, mapping(Discount::getAmount, toList())))
                .entrySet()
                .stream()
                .map(entry -> Discount.discount(entry.getKey(), entry.getValue().stream().reduce(BigDecimal::add).orElse(ZERO)))
                .collect(toList());
    }

    private static AggregationReceipt countTotal(AggregationReceipt subtotal, BigDecimal discount) {
        return total(subtotal.getAggregation().subtract(discount));
    }

    private static BigDecimal countTotalDiscount(List<Discount> aggregatedDiscounts) {
        return aggregatedDiscounts.stream().map(Discount::getAmount).reduce(BigDecimal::add).orElse(ZERO);
    }
}
