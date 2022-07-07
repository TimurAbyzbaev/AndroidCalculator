package com.example.androidcalculator.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.androidcalculator.R;

public enum Theme {
    ONE(R.style.Theme_AndroidCalculator,R.string.theme_1, "themeone"),
    TWO(R.style.Theme2, R.string.theme_2, "themetwo");

    @StyleRes
    private int themeRes;
    @StringRes
    private int title;

    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
