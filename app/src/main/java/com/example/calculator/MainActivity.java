package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText num1;
    public EditText num2;
    public Spinner spinner;
    public TextView Average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        spinner = findViewById(R.id.spinner);
        Average = findViewById(R.id.average);
        Button calculateButton = findViewById(R.id.calculate_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.FC_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    public void calculateResult() {
        String op1String = num1.getText().toString();
        String op2String = num2.getText().toString();

        if (op1String.isEmpty() || op2String.isEmpty()) {
            Toast.makeText(this, "Please Enter Both The Numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double Number1, Number2;
        try {
            Number1 = Integer.parseInt(op1String);
            Number2 = Integer.parseInt(op2String);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid The Number Format", Toast.LENGTH_SHORT).show();
            return;
        }

        String Calc_Num = spinner.getSelectedItem().toString();
        double SumAverage = 0;
        boolean valid_NUmber = true;

        try {
            switch (Calc_Num) {
                case "+":
                     SumAverage = Number1 + Number2;
                    break;
                case "-":
                    SumAverage =   Number1 - Number2;
                    break;
                case "*":
                    SumAverage = Number1 * Number2;
                    break;
                case "/":
                    if (Number2 == 0) {
                        Toast.makeText(this, "Cannot The divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SumAverage = (Number1 / Number2);
                    break;
                case "^":
                    SumAverage = (int) Math.pow(Number1, Number2);
                    break;
                default:
                    valid_NUmber = false;
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error performing calculation", Toast.LENGTH_SHORT).show();
            return;
        }

        if (valid_NUmber) {
            Average.setText("Average:"  +SumAverage);
        } else {
            Toast.makeText(this, "Invalid operation", Toast.LENGTH_SHORT).show();
        }
    }
}
