package com.airtribe.library.strategy;

public interface FeeCalculator {
    double calculate(long days, double bookPrice);
}
