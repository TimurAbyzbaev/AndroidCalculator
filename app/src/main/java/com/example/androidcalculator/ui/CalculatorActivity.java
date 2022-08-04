package com.example.androidcalculator.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidcalculator.R;
import com.example.androidcalculator.model.CalculatorImpl;
import com.example.androidcalculator.model.Operations;
import com.example.androidcalculator.model.Theme;
import com.example.androidcalculator.model.ThemeRepository;
import com.example.androidcalculator.model.ThemeRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView resultText;
    private CalculatorPresenter presenter;
    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.textView);
        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.zero, 0);
        digits.put(R.id.one, 1);
        digits.put(R.id.two, 2);
        digits.put(R.id.three, 3);
        digits.put(R.id.four, 4);
        digits.put(R.id.five, 5);
        digits.put(R.id.six, 6);
        digits.put(R.id.seven, 7);
        digits.put(R.id.eight, 8);
        digits.put(R.id.nine, 9);


        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };

        findViewById(R.id.zero).setOnClickListener(digitClickListener);
        findViewById(R.id.one).setOnClickListener(digitClickListener);
        findViewById(R.id.two).setOnClickListener(digitClickListener);
        findViewById(R.id.three).setOnClickListener(digitClickListener);
        findViewById(R.id.four).setOnClickListener(digitClickListener);
        findViewById(R.id.five).setOnClickListener(digitClickListener);
        findViewById(R.id.six).setOnClickListener(digitClickListener);
        findViewById(R.id.seven).setOnClickListener(digitClickListener);
        findViewById(R.id.eight).setOnClickListener(digitClickListener);
        findViewById(R.id.nine).setOnClickListener(digitClickListener);


        Map<Integer, Operations> operators = new HashMap<>();
        operators.put(R.id.plus, Operations.ADD);
        operators.put(R.id.minus, Operations.SUB);
        operators.put(R.id.multiplication, Operations.MULT);
        operators.put(R.id.divider, Operations.DIV);


        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };
        findViewById(R.id.plus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.minus).setOnClickListener(operatorsClickListener);
        findViewById(R.id.multiplication).setOnClickListener(operatorsClickListener);
        findViewById(R.id.divider).setOnClickListener(operatorsClickListener);

        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();

                Theme selectedTheme = (Theme) intent.getSerializableExtra(ThemeSelectActivity.EXTRA_THEME);

                themeRepository.saveTheme(selectedTheme);
                recreate();
            }
        });

        findViewById(R.id.dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });
        findViewById(R.id.ac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAcPressed();
            }
        });
        findViewById(R.id.plusMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPlusMinusPressed();
            }
        });
        findViewById(R.id.percent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPercentPressed();
            }
        });

        findViewById(R.id.equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEqualsPressed();
            }
        });

        findViewById(R.id.themeSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, ThemeSelectActivity.class);
                intent.putExtra(ThemeSelectActivity.EXTRA_THEME, themeRepository.getSavedTheme());
                themeLauncher.launch(intent);
            }
        });


    }

    @Override
    public void showResult(String result) {
        resultText.setText(result);
    }
}