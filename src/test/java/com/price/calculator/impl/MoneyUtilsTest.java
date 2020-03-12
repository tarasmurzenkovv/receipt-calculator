package com.price.calculator.impl;

import org.junit.jupiter.api.Test;

import static com.price.calculator.service.MoneyUtils.format;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class MoneyUtilsTest {
    @Test
    public void shouldProperlyFormatFractionNumbers() {
        assertThat(format(valueOf(0.00001))).isEqualTo("£0.00");
    }
}
