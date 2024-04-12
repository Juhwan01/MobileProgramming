package com.example.first;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bPlus = (Button) findViewById(R.id.buttonPlus);
        Button bMinus=(Button) findViewById(R.id.buttonMinus);
        Button bDiv = (Button) findViewById(R.id.buttonDivide);
        Button bMulti = (Button) findViewById(R.id.buttonMultiply);
        EditText eText1 = (EditText) findViewById(R.id.edit1);
        EditText eText2 = (EditText) findViewById(R.id.edit2);
        EditText eText3 = (EditText) findViewById(R.id.editResult);
        bPlus.setOnClickListener(e -> {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();
            int result = Integer.parseInt(s1) + Integer.parseInt(s2);
            eText3.setText("" + result);
        });
        bMinus.setOnClickListener(e -> {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();
            int result = Integer.parseInt(s1) - Integer.parseInt(s2);
            eText3.setText("" + result);
        });
        bDiv.setOnClickListener(e -> {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();
            int result = Integer.parseInt(s1) / Integer.parseInt(s2);
            eText3.setText("" + result);
        });
        bMulti.setOnClickListener(e -> {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();
            int result = Integer.parseInt(s1) * Integer.parseInt(s2);
            eText3.setText("" + result);
        });
    }
}