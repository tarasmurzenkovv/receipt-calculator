package com.price.calculator.service.impl.printer;

import com.price.calculator.model.Receipt;
import com.price.calculator.service.receipt.ReceiptRender;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptPrinter {
    private static final List<ReceiptRender> RECEIPT_RENDERS = build();

    public String render(Receipt receipt) {
        return RECEIPT_RENDERS
                .stream()
                .map(r -> r.render(receipt))
                .collect(Collectors.joining());
    }

    public void add(ReceiptRender receiptRender) {
        RECEIPT_RENDERS.add(receiptRender);
    }

    @SneakyThrows
    public void write(OutputStream outputStream, InputStream receiptAsStream) {
        outputStream.write(receiptAsStream.readAllBytes());
    }

    private static List<ReceiptRender> build() {
        final var renders = new LinkedList<ReceiptRender>();
        renders.add(new HeaderReceiptRender());
        renders.add(new BodyRender());
        renders.add(new FooterReceiptRender());
        return renders;
    }
}
