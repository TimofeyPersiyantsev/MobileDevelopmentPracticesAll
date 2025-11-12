package com.example.applicationallpz.fragments.practice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.applicationallpz.R;

public class Practice2Fragment extends Fragment implements View.OnClickListener {

    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;
    private Button buttonClean, buttonEquals, buttonDot, buttonPercent;
    private Button buttonPower, buttonSqrt;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    private TextView operation, result;
    private EditText number1, number2;

    private boolean newCalculation = true;

    public Practice2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.practices2fragment, container, false);

        initializeViews(view);
        setClickListeners();

        return view;
    }

    private void initializeViews(View view) {
        // Основные операции
        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonSubtract = view.findViewById(R.id.buttonSubtract);
        buttonMultiply = view.findViewById(R.id.buttonMultiply);
        buttonDivide = view.findViewById(R.id.buttonDivide);
        buttonClean = view.findViewById(R.id.buttonClean);
        buttonEquals = view.findViewById(R.id.buttonEquals);
        buttonDot = view.findViewById(R.id.buttonDot);
        buttonPercent = view.findViewById(R.id.buttonPercent);
        buttonPower = view.findViewById(R.id.buttonPower);
        buttonSqrt = view.findViewById(R.id.buttonSqrt);

        // Цифровые кнопки
        button0 = view.findViewById(R.id.button0);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);
        button7 = view.findViewById(R.id.button7);
        button8 = view.findViewById(R.id.button8);
        button9 = view.findViewById(R.id.button9);

        // Текстовые поля
        operation = view.findViewById(R.id.operation);
        result = view.findViewById(R.id.result);
        number1 = view.findViewById(R.id.number1);
        number2 = view.findViewById(R.id.number2);
    }

    private void setClickListeners() {
        // Основные операции
        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonClean.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonPower.setOnClickListener(this);
        buttonSqrt.setOnClickListener(this);

        // Цифровые кнопки
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // Обработка цифровых кнопок
        if (id == R.id.button0 || id == R.id.button1 || id == R.id.button2 ||
                id == R.id.button3 || id == R.id.button4 || id == R.id.button5 ||
                id == R.id.button6 || id == R.id.button7 || id == R.id.button8 ||
                id == R.id.button9) {

            handleNumberInput(((Button) v).getText().toString());
            return;
        }

        // Обработка остальных кнопок через if-else
        if (id == R.id.buttonDot) {
            handleDotInput();
        } else if (id == R.id.buttonAdd) {
            setOperation("+");
        } else if (id == R.id.buttonSubtract) {
            setOperation("-");
        } else if (id == R.id.buttonMultiply) {
            setOperation("×");
        } else if (id == R.id.buttonDivide) {
            setOperation("/");
        } else if (id == R.id.buttonPercent) {
            handlePercent();
        } else if (id == R.id.buttonPower) {
            handlePower();
        } else if (id == R.id.buttonSqrt) {
            handleSquareRoot();
        } else if (id == R.id.buttonEquals) {
            calculateResult();
        } else if (id == R.id.buttonClean) {
            clearAll();
        }
    }

    private void handleNumberInput(String number) {
        if (newCalculation) {
            clearAll();
            newCalculation = false;
        }

        if (operation.getText().toString().isEmpty()) {
            // Ввод в первое число
            String currentText = number1.getText().toString();
            if (currentText.equals("0")) {
                number1.setText(number);
            } else {
                number1.setText(currentText + number);
            }
        } else {
            // Ввод во второе число
            String currentText = number2.getText().toString();
            if (currentText.equals("0")) {
                number2.setText(number);
            } else {
                number2.setText(currentText + number);
            }
        }
    }

    private void handleDotInput() {
        if (newCalculation) {
            clearAll();
            newCalculation = false;
        }

        if (operation.getText().toString().isEmpty()) {
            // Точка для первого числа
            String currentText = number1.getText().toString();
            if (currentText.isEmpty()) {
                number1.setText("0.");
            } else if (!currentText.contains(".")) {
                number1.setText(currentText + ".");
            }
        } else {
            // Точка для второго числа
            String currentText = number2.getText().toString();
            if (currentText.isEmpty()) {
                number2.setText("0.");
            } else if (!currentText.contains(".")) {
                number2.setText(currentText + ".");
            }
        }
    }

    private void setOperation(String op) {
        if (newCalculation) {
            newCalculation = false;
        }

        // Если второе число уже введено, сначала вычисляем результат
        if (!number2.getText().toString().isEmpty() && !operation.getText().toString().isEmpty()) {
            calculateResult();
            // Результат становится первым числом для новой операции
            number1.setText(result.getText().toString());
            number2.setText("");
        }

        operation.setText(op);
    }

    private void handlePercent() {
        try {
            String num1Str = number1.getText().toString();
            String num2Str = number2.getText().toString();

            if (num1Str.isEmpty()) return;

            float num1 = Float.parseFloat(num1Str);
            float res;

            if (num2Str.isEmpty()) {
                // Процент от первого числа (1%)
                res = num1 / 100;
                result.setText(formatResult(res));
            } else {
                // Процент от первого числа (num2%)
                float num2 = Float.parseFloat(num2Str);
                res = (num1 * num2) / 100;
                result.setText(formatResult(res));
            }
            operation.setText("%");

        } catch (NumberFormatException e) {
            result.setText("Ошибка");
        }
    }

    private void handlePower() {
        try {
            String num1Str = number1.getText().toString();
            if (num1Str.isEmpty()) return;

            float num1 = Float.parseFloat(num1Str);
            float res = num1 * num1;

            result.setText(formatResult(res));
            operation.setText("x²");
            newCalculation = true;

        } catch (NumberFormatException e) {
            result.setText("Ошибка");
        }
    }

    private void handleSquareRoot() {
        try {
            String num1Str = number1.getText().toString();
            if (num1Str.isEmpty()) return;

            float num1 = Float.parseFloat(num1Str);
            if (num1 >= 0) {
                float res = (float) Math.sqrt(num1);
                result.setText(formatResult(res));
                operation.setText("√");
                newCalculation = true;
            } else {
                result.setText("Ошибка");
            }
        } catch (NumberFormatException e) {
            result.setText("Ошибка");
        }
    }

    private void calculateResult() {
        try {
            String num1Str = number1.getText().toString();
            String num2Str = number2.getText().toString();
            String currentOperation = operation.getText().toString();

            if (num1Str.isEmpty() || num2Str.isEmpty() || currentOperation.isEmpty()) {
                return;
            }

            float num1 = Float.parseFloat(num1Str);
            float num2 = Float.parseFloat(num2Str);
            float res = 0;

            switch (currentOperation) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "×":
                    res = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        res = num1 / num2;
                    } else {
                        result.setText("Ошибка");
                        return;
                    }
                    break;
                default:
                    return;
            }

            result.setText(formatResult(res));
            newCalculation = true;

        } catch (NumberFormatException e) {
            result.setText("Ошибка");
        }
    }

    private String formatResult(float res) {
        if (res == (long) res) {
            return String.valueOf((long) res);
        } else {
            // Ограничиваем количество знаков после запятой
            String formatted = String.format("%.6f", res);
            // Убираем лишние нули в конце
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            return formatted;
        }
    }

    private void clearAll() {
        number1.setText("");
        number2.setText("");
        operation.setText("");
        result.setText("");
        newCalculation = true;
    }
}
