package com.example.androidcalculator.model;

public class CalculatorImpl implements Calculator {

    @Override
    public double perform(double arg1, double arg2, Operations operations) {
        switch (operations) {
            case ADD:
                return arg1 + arg2;
            case SUB:
                return arg1 - arg2;
            case DIV:
                return arg1 / arg2;
            case MULT:
                return arg1 * arg2;
        }
        return 0.0;
    }
}
