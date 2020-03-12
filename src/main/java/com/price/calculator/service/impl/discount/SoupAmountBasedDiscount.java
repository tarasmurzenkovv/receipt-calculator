package com.price.calculator.service.impl.discount;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Discount;
import com.price.calculator.service.discount.DiscountProgram;

import java.math.BigDecimal;

import static com.price.calculator.model.Product.BREAD;
import static com.price.calculator.model.Product.SOUP;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.UP;

public class SoupAmountBasedDiscount implements DiscountProgram {
    @Override
    public Discount apply(Demand demand) {
        return Discount.discount("Loaf of bread for half price", calculateDiscount());
    }

    @Override
    public boolean isActive(Demand demand) {
        return demand.getProduct() == SOUP && demand.getAmount() == 2d;
    }

    private static BigDecimal calculateDiscount() {
        return BREAD
                .getPrice()
                .divide(valueOf(2), UP);
    }
}
