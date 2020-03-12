package com.price.calculator.service.impl.printer;

import com.price.calculator.model.Discount;
import com.price.calculator.model.Receipt;

import java.util.stream.Collectors;

import static com.price.calculator.service.MoneyUtils.format;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class BodyRender extends AbstractReceiptRender {
    @Override
    public String render(Receipt receipt) {
        final var rightOffset = maxLengthOfColumnsDescriptions(receipt);
        final var leftOffset = maxPriceLength(receipt);
        return receipt.getDiscounts()
                .stream()
                .map(discount -> buildRow(discount, rightOffset, leftOffset))
                .collect(Collectors.joining());
    }

    private static String buildRow(Discount discount, int rightOffset, int leftOffset) {
        final var description = discount.getDescription();
        final var amount = format(discount.getAmount());
        return rightPad(description, rightOffset) + leftPad(amount, leftOffset) + "\n";
    }
}
