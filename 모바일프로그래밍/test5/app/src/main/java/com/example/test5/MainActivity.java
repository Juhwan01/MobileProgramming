package com.example.test5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        TextView number1 = new TextView(this);
        number1.setText("Number 1");

        et1 = new EditText(this);
        et1.setText("");

        TextView number2 = new TextView(this);
        number2.setText("Number 2");

        et2 = new EditText(this);
        et2.setText("");

        Button plus_btn = new Button(this);
        plus_btn.setText("+");
        plus_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                OnPlus(v);
            }
        });

        Button min_btn = new Button(this);
        min_btn.setText("-");
        min_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                OnMin(v);
            }
        });

        Button mix_btn = new Button(this);
        mix_btn.setText("*");
        mix_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                OnMix(v);
            }
        });

        Button div_btn = new Button(this);
        div_btn.setText("/");
        div_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                OnDiv(v);
            }
        });

        TextView result = new TextView(this);
        result.setText("Result");

        et3 = new EditText(this);
        et3.setText("");

        container.addView(number1);
        container.addView(et1);
        container.addView(number2);
        container.addView(et2);
        container.addView(plus_btn);
        container.addView(min_btn);
        container.addView(mix_btn);
        container.addView(div_btn);
        container.addView(result);
        container.addView(et3);
        setContentView(container);
    }
    public void OnPlus(View v) {
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        int result = Integer.parseInt(s1) + Integer.parseInt(s2);
        et3.setText(String.valueOf(result));
    }

    public void OnMin(View v) {
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        int result = Integer.parseInt(s1) - Integer.parseInt(s2);
        et3.setText(String.valueOf(result));
    }

    public void OnMix(View v) {
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        int result = Integer.parseInt(s1) * Integer.parseInt(s2);
        et3.setText(String.valueOf(result));
    }

    public void OnDiv(View v) {
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        int result = Integer.parseInt(s1) / Integer.parseInt(s2);
        et3.setText(String.valueOf(result));
    }
}