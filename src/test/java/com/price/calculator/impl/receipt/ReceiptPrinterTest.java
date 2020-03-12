package com.price.calculator.impl.receipt;

import com.price.calculator.service.impl.printer.ReceiptPrinter;
import org.junit.jupiter.api.Test;

import static com.price.calculator.model.AggregationReceipt.subTotal;
import static com.price.calculator.model.AggregationReceipt.total;
import static com.price.calculator.model.Discount.discount;
import static com.price.calculator.model.Receipt.builder;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

public class ReceiptPrinterTest {
    private final ReceiptPrinter receiptPrinter = new ReceiptPrinter();

    @Test
    public void shouldRenderToStringProperlyForEmptyDiscounts() {
        final var receipt = builder()
                .subTotal(subTotal(valueOf(2)))
                .total(total(valueOf(2)))
                .build();
        assertThat(receiptPrinter.render(receipt))
                .isNotBlank()
                .isEqualTo("Total:                   £2.00\n"
                         + "(No offers available)         \n"
                         + "Subtotal:                £2.00\n");
    }

    @Test
    public void shouldRenderToStringProperlyForNonEmptyDiscounts() {
        final var receipt = builder()
                .subTotal(subTotal(valueOf(2)))
                .discounts(of(discount("Discount", valueOf(3))))
                .total(total(valueOf(2)))
                .build();
        assertThat(receiptPrinter.render(receipt))
                .isNotBlank()
                .isEqualTo("Total:                   £2.00\n" +
                           "Discount                 £3.00\n" +
                           "Subtotal:                £2.00\n");
    }
}
