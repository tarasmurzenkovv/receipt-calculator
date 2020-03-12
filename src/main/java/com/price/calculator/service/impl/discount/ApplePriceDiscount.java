package com.price.calculator.service.impl.discount;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Discount;
import com.price.calculator.service.discount.DiscountProgram;
import com.price.calculator.service.impl.DateTimeService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.price.calculator.model.Product.APPLE;
import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.of;

@RequiredArgsConstructor
public class ApplePriceDiscount implements DiscountProgram {
    private static final double DISCOUNT = 0.02;
    private static final LocalDate START_DISCOUNTING_DATE = of(2019, 1, 1);
    private static final LocalDate END_DISCOUNTING_DATE = of(2019, 6, 1);
    private final DateTimeService dateTimeService;

    @Override
    public Discount apply(Demand demand) {
        return Discount.discount(format("Apples '%f%%' off", 100 * DISCOUNT), calculateDiscount(demand));
    }

    @Override
    public boolean isActive(Demand demand) {
        return demand.getProduct() == APPLE
                && dateTimeService.isCurrentDateWithinRange(START_DISCOUNTING_DATE, END_DISCOUNTING_DATE);
    }

    private static BigDecimal calculateDiscount(Demand demand) {
        final var price = demand.getProduct().getPrice();
        return price.multiply(valueOf(DISCOUNT));
    }
}