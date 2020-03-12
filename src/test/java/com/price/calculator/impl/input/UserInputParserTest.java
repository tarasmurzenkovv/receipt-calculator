package com.price.calculator.impl.input;

import com.price.calculator.service.input.UserInputParser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static com.price.calculator.model.Product.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInputParserTest {
    private final UserInputParser userInputParser = new UserInputParser();

    @Test
    public void shouldProperlyParseValidaString() {
        final var validString = "PriceBasket Apple Milk Bread";
        final var parsedDemands = userInputParser.parse(new ByteArrayInputStream(validString.getBytes()));
        assertThat(parsedDemands)
                .isNotEmpty()
                .containsExactlyInAnyOrder(APPLE, MILK, BREAD);
    }
}
