package com.price.calculator.service.impl.printer;

import com.price.calculator.model.Receipt;
import com.price.calculator.service.MoneyUtils;

import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class FooterReceiptRender extends AbstractReceiptRender {
    @Override
    public String render(Receipt receipt) {
        final var rightOffset = maxLengthOfColumnsDescriptions(receipt);
        final var leftOffset = maxPriceLength(receipt);
        final var description = receipt.getSubTotal().getDescription();
        final var formattedTotalPrice = MoneyUtils.format(receipt.getSubTotal().getAggregation());
        return rightPad(description, rightOffset) + leftPad(formattedTotalPrice, leftOffset) + "\n";
    }
}
