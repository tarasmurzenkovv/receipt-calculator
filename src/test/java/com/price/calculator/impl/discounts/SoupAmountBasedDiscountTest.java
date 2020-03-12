package com.price.calculator.impl.discounts;

import com.price.calculator.service.discount.DiscountProgram;
import com.price.calculator.service.impl.discount.SoupAmountBasedDiscount;
import org.junit.jupiter.api.Test;

import static com.price.calculator.model.Demand.demand;
import static com.price.calculator.model.Discount.discount;
import static com.price.calculator.model.Product.BREAD;
import static com.price.calculator.model.Product.SOUP;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.UP;
import static org.assertj.core.api.Assertions.assertThat;

public class SoupAmountBasedDiscountTest {
    private final DiscountProgram sut = new SoupAmountBasedDiscount();

    @Test
    public void shouldGenerateProperAmount() {
        final var soup = demand(SOUP, 1);
        final var discount = sut.apply(soup);
        final var price = BREAD.getPrice().divide(valueOf(2), UP);
        assertThat(discount).isEqualTo(discount("Loaf of bread for half price", price));
    }

    @Test
    public void shouldBeApplicableWhenCurrentProductIsSoupAndAmountIsTwo() {
        assertThat(sut.isActive(demand(SOUP, 2))).isTrue();
    }

    @Test
    public void shouldNotBeApplicableWhenCurrentProductIsNotSoupButAmountIsTwo() {
        assertThat(sut.isActive(demand(BREAD, 2))).isFalse();
    }

    @Test
    public void shouldNotBeApplicableWhenCurrentProductIsSoupButAmountIsNotTwo() {
        assertThat(sut.isActive(demand(BREAD, 3))).isFalse();
    }
}
