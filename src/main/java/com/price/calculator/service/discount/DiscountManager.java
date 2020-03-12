package com.price.calculator.service.discount;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Discount;
import com.price.calculator.service.impl.DateTimeService;
import com.price.calculator.service.impl.discount.ApplePriceDiscount;
import com.price.calculator.service.impl.discount.DefaultNoDiscount;
import com.price.calculator.service.impl.discount.SoupAmountBasedDiscount;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DiscountManager {
    private static final List<DiscountProgram> DISCOUNT_PROGRAMS = build();

    public Collection<Discount> applyDiscountPrograms(Demand demand) {
        return DISCOUNT_PROGRAMS.stream()
                .filter(program -> program.isActive(demand))
                .map(program -> program.apply(demand))
                .collect(toList());
    }

    public void addDiscountProgram(DiscountProgram discountProgram) {
        DISCOUNT_PROGRAMS.add(discountProgram);
    }

    private static List<DiscountProgram> build() {
        final var list = new LinkedList<DiscountProgram>();
        list.add(new SoupAmountBasedDiscount());
        list.add(new ApplePriceDiscount(new DateTimeService()));
        list.add(new DefaultNoDiscount());
        return list;
    }
}
