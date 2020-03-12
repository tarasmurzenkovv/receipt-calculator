package com.price.calculator.service.discount;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Discount;

public interface DiscountProgram {
    Discount apply(Demand demand);

    boolean isActive(Demand demand);
}
