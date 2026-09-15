package com.airtribe.library.strategy;

public class FixedFeeCalculator implements FeeCalculator{
    private static final double BASE_PRICE = 60.0;
    private static final double INCREMENT = 10.0;

    @Override
    public double calculate(long days, double bookPrice) {
        return BASE_PRICE + days * INCREMENT;
    }
}
