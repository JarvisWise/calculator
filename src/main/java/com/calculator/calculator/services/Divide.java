package com.calculator.calculator.services;

import org.springframework.stereotype.Service;

@Service
public class Divide implements Operation{
    public final static String NAME = "divide";
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public double actionOfOperation(double a, double b) {
        return a / b;
    }
}
