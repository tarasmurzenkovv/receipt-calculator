package com.price.calculator.impl.discounts;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Discount;
import com.price.calculator.model.Product;
import com.price.calculator.service.discount.DiscountProgram;
import com.price.calculator.service.impl.discount.DefaultNoDiscount;
import org.junit.jupiter.api.Test;

import static com.price.calculator.model.Demand.demand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DefaultNoDiscountTest {
    private final DiscountProgram sut = new DefaultNoDiscount();

    @Test
    public void shouldNotGenerateAnyAdditionAmount() {
        final var currentDemand = demand(Product.BREAD, 2);
        assertThat(sut.apply(currentDemand))
                .isEqualTo(Discount.empty());
    }

    @Test
    public void shouldNotBeApplicable() {
        assertThat(sut.isActive(mock(Demand.class))).isFalse();
    }
}
