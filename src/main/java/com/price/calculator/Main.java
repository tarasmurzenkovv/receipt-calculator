package com.price.calculator;

import com.price.calculator.model.Basket;
import com.price.calculator.service.demand.DemandManager;
import com.price.calculator.service.discount.DiscountManager;
import com.price.calculator.service.impl.printer.ReceiptPrinter;
import com.price.calculator.service.input.UserInputParser;
import com.price.calculator.service.receipt.ReceiptManager;

import java.io.ByteArrayInputStream;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final var userInputParser = new UserInputParser();
        final var receiptPrinter = new ReceiptPrinter();
        final var discountManager = new DiscountManager();
        final var receiptManager = new ReceiptManager();
        final var demandManager = new DemandManager();
        final var products = userInputParser.parse(System.in);
        final var demands = demandManager.extractDemand(products);
        final var discounts = demands.stream()
                .flatMap(demand -> discountManager.applyDiscountPrograms(demand).stream())
                .collect(Collectors.toList());
        final var basket = Basket.basket(discounts, products);
        final var receipt = receiptManager.generate(basket);
        final var renderedToStringReceipt = receiptPrinter.render(receipt);
        receiptPrinter.write(System.out, new ByteArrayInputStream(renderedToStringReceipt.getBytes()));
    }
}