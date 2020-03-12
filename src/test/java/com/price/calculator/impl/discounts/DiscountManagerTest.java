package com.price.calculator.impl.discounts;

import com.price.calculator.service.discount.DiscountManager;
import org.junit.jupiter.api.Test;

import static com.price.calculator.model.Demand.demand;
import static com.price.calculator.model.Discount.discount;
import static com.price.calculator.model.Product.BREAD;
import static com.price.calculator.model.Product.SOUP;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class DiscountManagerTest {
    private final DiscountManager sut = new DiscountManager();

    @Test
    public void shouldAddAmountForSoup() {
        final var soupDemand = demand(SOUP, 2);
        final var expectedDiscounts = of(discount("Loaf of bread for half price",  BREAD.getPrice().divide(valueOf(2))));
        assertThat(sut.applyDiscountPrograms(soupDemand)).isNotEmpty().isEqualTo(expectedDiscounts);
    }

    @Test
    public void shouldNotAddAmountForNonSoup() {
        assertThat(sut.applyDiscountPrograms(demand(BREAD, 2))).isEmpty();
    }
}
