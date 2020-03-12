package com.price.calculator.service.input;

import com.price.calculator.model.Demand;
import com.price.calculator.model.Product;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

import static com.price.calculator.model.Demand.demand;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;
import static org.apache.commons.lang3.StringUtils.*;

public class UserInputParser {
    private static final String GREETING_STRING = "PriceBasket";

    @SneakyThrows
    public List<Product> parse(InputStream inputStream) {
        final var inputAsString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        final var withoutGreeting = inputAsString.replace(GREETING_STRING, EMPTY);
        return extractProduct(withoutGreeting);
    }

    private List<Product> extractProduct(String withoutGreeting) {
        return stream(withoutGreeting.split(SPACE))
                .map(String::toUpperCase)
                .filter(string -> !StringUtils.isEmpty(string))
                .map(Product::from)
                .collect(toList());
    }
}
