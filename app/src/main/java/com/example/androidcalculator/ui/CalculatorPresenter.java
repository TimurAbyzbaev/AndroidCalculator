package com.example.androidcalculator.ui;

import android.util.Log;

import com.example.androidcalculator.model.Calculator;
import com.example.androidcalculator.model.Operations;

import java.text.DecimalFormat;
import java.util.logging.Logger;

public class CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;
    private double argOne;
    private Double argTwo;
    private Operations selectedOperator;
    private DecimalFormat formater = new DecimalFormat("#.###################");
    private boolean isFloat = false;
    private int iter = -1;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {

        if (argTwo == null) {
            if (!isFloat) {
                argOne = argOne * 10 + digit;
                showNumberFormated(argOne);
            } else {
                argOne = argOne + digit * Math.pow(10, iter);
                showNumberFormated(argOne);
                iter--;
            }
        } else {
            if (!isFloat) {
                argTwo = argTwo * 10 + digit;
                showNumberFormated(argTwo);
            } else {
                argTwo = argTwo + digit * Math.pow(10, iter);
                showNumberFormated(argTwo);
                iter--;
            }
        }

    }

    public void onOperatorPressed(Operations operations) {
        if (selectedOperator != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showNumberFormated(argOne);
        }
        selectedOperator = operations;
        argTwo = 0.0;
        isFloat = false;
        iter = -1;
    }


    public void onDotPressed() {
        isFloat = true;
    }

    public void onAcPressed() {
        argOne = 0;
        argTwo = null;
        selectedOperator = null;
        showNumberFormated(argOne);
    }

    public void onPlusMinusPressed() {
        if (argTwo == null) {
            argOne = argOne * (-1);
            showNumberFormated(argOne);
        } else {
            argTwo = argTwo * (-1);
            showNumberFormated(argTwo);
        }
    }

    public void onPercentPressed() {
        if (argTwo == null) {
            argOne /= 100;
            showNumberFormated(argOne);
        } else {
            argTwo = argTwo * argOne / 100;
        }
    }

    public void onEqualsPressed() {
        if (selectedOperator != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            argTwo = null;
            selectedOperator = null;
            showNumberFormated(argOne);
        }
    }

    private void showNumberFormated(double value) {
        view.showResult(formater.format(value));
    }
}
