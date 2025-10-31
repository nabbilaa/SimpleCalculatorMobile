package com.example.calculator;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MoveActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText number1, number2;
    private Button plus, minus, multiply, divide, btnCalculate;
    private TextView tvResult;

    private static final String STATE_RESULT = "state_result";
    private String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        // Set click listener
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);

        // restore hasil kalau rotate layar
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // Set operasi berdasarkan tombol operator
        if (id == R.id.plus) {
            operation = "+";
            showToast("Operasi: Penjumlahan");
        } else if (id == R.id.minus) {
            operation = "-";
            showToast("Operasi: Pengurangan");
        } else if (id == R.id.multiply) {
            operation = "*";
            showToast("Operasi: Perkalian");
        } else if (id == R.id.divide) {
            operation = "/";
            showToast("Operasi: Pembagian");
        } else if (id == R.id.btn_calculate) {
            calculate();
        }
    }

    private void calculate() {
        String inputNum1 = number1.getText().toString().trim();
        String inputNum2 = number2.getText().toString().trim();

        boolean isEmptyFields = false;

        if (TextUtils.isEmpty(inputNum1)) {
            isEmptyFields = true;
            number1.setError("Harap isi angka pertama");
        }
        if (TextUtils.isEmpty(inputNum2)) {
            isEmptyFields = true;
            number2.setError("Harap isi angka kedua");
        }
        if (TextUtils.isEmpty(operation)) {
            Toast.makeText(this, "Pilih operasi terlebih dahulu (+, -, ×, ÷)", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isEmptyFields) {
            double num1 = Double.parseDouble(inputNum1);
            double num2 = Double.parseDouble(inputNum2);
            double result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Tidak bisa dibagi dengan nol", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            int hasil = (int) result;
            tvResult.setText(String.valueOf(result));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
}
