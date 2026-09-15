package com.airtribe.library.strategy;

public class BookDependentCalculator implements FeeCalculator{
    private static final double BASE_PRICE = 40.0;
    private static final double PERCENTAGE = 0.02;

    @Override
    public double calculate(long days, double bookPrice) {
        return BASE_PRICE + days * bookPrice * PERCENTAGE;
    }
}
