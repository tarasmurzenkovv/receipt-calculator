package com.price.calculator.impl.receipt;

import com.price.calculator.service.receipt.ReceiptManager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.price.calculator.model.Basket.basket;
import static com.price.calculator.model.Discount.discount;
import static com.price.calculator.model.Product.APPLE;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ReceiptManagerTest {
    private final ReceiptManager receiptManager = new ReceiptManager();

    @Test
    public void shouldProperlyCalculateNormalBasket() {
        final var discounts = of(discount("First description", ONE));
        final var products = of(APPLE);
        final var basket = basket(discounts, products);
        final var receipt = receiptManager.generate(basket);
        assertThat(receipt).isNotNull();
        assertThat(receipt.getDiscounts()).isEqualTo(discounts);
        assertThat(receipt.getSubTotal().getAggregation()).isEqualTo(ONE);
        assertThat(receipt.getSubTotal().getDescription()).isEqualTo("Subtotal:");
        assertThat(receipt.getTotal().getAggregation()).isEqualTo(BigDecimal.ZERO);
        assertThat(receipt.getTotal().getDescription()).isEqualTo("Total:");
    }

    @Test
    public void shouldProperlyProcessBasketWithoutDiscounts() {
        final var products = of(APPLE);
        final var basket = basket(null, products);
        final var receipt = receiptManager.generate(basket);
        assertThat(receipt).isNotNull();
        assertThat(receipt.getDiscounts()).isEmpty();
        assertThat(receipt.getSubTotal().getAggregation()).isEqualTo(ONE);
        assertThat(receipt.getSubTotal().getDescription()).isEqualTo("Subtotal:");
        assertThat(receipt.getTotal().getAggregation()).isEqualTo(ONE);
        assertThat(receipt.getTotal().getDescription()).isEqualTo("Total:");
    }

    @Test
    public void shouldProperlyProcessBasketWithGrouppingDiscounts() {
        final var firstDiscount = discount("First description", valueOf(0.5));
        final var secondDiscount = discount("First description", valueOf(0.5));
        final var discounts = of(firstDiscount, secondDiscount);
        final var products = of(APPLE);
        final var basket = basket(discounts, products);
        final var receipt = receiptManager.generate(basket);
        assertThat(receipt).isNotNull();
        assertThat(receipt.getDiscounts()).isEqualTo(of(discount("First description", valueOf(1.0))));
        assertThat(receipt.getSubTotal().getAggregation()).isEqualTo(ONE);
        assertThat(receipt.getSubTotal().getDescription()).isEqualTo("Subtotal:");
        assertThat(receipt.getTotal().getAggregation()).isEqualTo(valueOf(0.0));
        assertThat(receipt.getTotal().getDescription()).isEqualTo("Total:");
    }
}
