package com.price.calculator.service.impl.discount;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Discount;
import com.price.calculator.service.discount.DiscountProgram;

import static com.price.calculator.model.Discount.empty;

public class DefaultNoDiscount implements DiscountProgram {
    @Override
    public Discount apply(Demand demand) {
        return empty();
    }

    @Override
    public boolean isActive(Demand demand) {
        return false;
    }
}
