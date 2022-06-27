package com.example.androidcalculator.ui;

import android.icu.number.NumberFormatter;
import android.icu.number.NumberRangeFormatter;

import com.example.androidcalculator.model.Calculator;
import com.example.androidcalculator.model.Operations;

import java.text.DecimalFormat;

public class CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;
    private double argOne;
    private Double argTwo;
    private Operations selectedOperator;
    private DecimalFormat formater = new DecimalFormat("#.#########");

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showNumberFormated(argOne);
        }
        else {
            argTwo = argTwo * 10 + digit;
            showNumberFormated(argTwo);
        }

    }

    public void onOperatorPressed(Operations operations) {
        if(selectedOperator != null){
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showNumberFormated(argOne);
            //view.showResult(String.valueOf(argOne));


        }
        selectedOperator = operations;
        argTwo = 0.0;
    }


    public void onDotPressed() {

    }

    public void onAcPressed() {
    }

    public void onPlusMinusPressed() {
    }

    public void onPercentPressed() {
    }

    public void onEqualsPressed() {
    }
    private void showNumberFormated(double value){
        view.showResult(formater.format(value));
    }
}
