package com.price.calculator.service.impl;

import java.time.LocalDate;

import static java.time.LocalDate.now;

public class DateTimeService {
    public LocalDate currentDate() {
        return now();
    }

    public boolean isCurrentDateWithinRange(LocalDate start, LocalDate end) {
        return currentDate().isAfter(start) && currentDate().isBefore(end);
    }
}
