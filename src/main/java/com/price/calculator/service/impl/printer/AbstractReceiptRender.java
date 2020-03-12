package com.price.calculator.service.impl.printer;

import com.price.calculator.model.Discount;
import com.price.calculator.model.Receipt;
import com.price.calculator.service.receipt.ReceiptRender;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.price.calculator.service.CollectionUtils.isEmpty;
import static com.price.calculator.service.MoneyUtils.format;
import static java.lang.Math.max;
import static java.util.List.of;
import static java.util.stream.Stream.concat;

public abstract class AbstractReceiptRender implements ReceiptRender {
    private static final int OFFSET = 2;
    protected static final String NO_OFFERS_AVAILABLE = "(No offers available)";

    public abstract String render(Receipt receipt);

    protected static int maxPriceLength(Receipt receipt) {
        return OFFSET + (isEmpty(receipt.getDiscounts())
                ? maxLengthOfPriceBase(receipt)
                : maxLengthOfPriceFull(receipt));
    }

    protected static int maxLengthOfColumnsDescriptions(Receipt receipt) {
        return OFFSET + (isEmpty(receipt.getDiscounts())
                ? maxLengthOfColumnsDescriptionsBase(receipt)
                : maxLengthColumnsDescriptionsFull(receipt));
    }

    private static int maxLengthOfPriceBase(Receipt receipt) {
        final var aggregations = of(receipt.getTotal().getAggregation(), receipt.getSubTotal().getAggregation()).stream();
        return maxLength(aggregations, price -> format(price).length(), 0);
    }

    private static int maxLengthOfPriceFull(Receipt receipt) {
        final var discounts = receipt.getDiscounts().stream().map(Discount::getAmount);
        final var aggregations = Stream.of(receipt.getTotal().getAggregation(), receipt.getSubTotal().getAggregation());
        return maxLength(concat(aggregations, discounts), price -> format(price).length(), 0);
    }

    private static int maxLengthOfColumnsDescriptionsBase(Receipt receipt) {
        final var totalPriceDescription = receipt.getTotal().getDescription();
        final var subtotalPriceDescription = receipt.getSubTotal().getDescription();
        final var offers = of(totalPriceDescription, subtotalPriceDescription, NO_OFFERS_AVAILABLE).stream();
        return maxLength(offers, String::length, 0);
    }

    private static int maxLengthColumnsDescriptionsFull(Receipt receipt) {
        final var discounts = receipt.getDiscounts().stream();
        final var maxLength = maxLength(discounts, discount -> discount.getDescription().length(), 0);
        return max(maxLength, maxLengthOfColumnsDescriptionsBase(receipt));
    }

    private static <T, R extends Comparable<R>> R maxLength(Stream<T> base, Function<T, R> mapper, R defaultValue) {
        return base.map(mapper).max(R::compareTo).orElse(defaultValue);
    }
}
