package com.example.test6_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String frist;
    String second;
    String cal;
    boolean cal_con = false;
    EditText result;
    EditText input1;
    EditText input2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result = (EditText) findViewById(R.id.result);
        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
    }
    public void OnNum(View v) {
        Button btn = (Button) v;
        if (cal_con == false)
        {
            frist = input1.getText().toString() + btn.getText().toString();
            input1.setText(frist);
            result.setText(frist);
        }
        else
        {
            second = input2.getText().toString() + btn.getText().toString();
            input2.setText(second);
            result.setText(frist+ cal + second);
        }
    }

    public void OnCal(View v)
    {
        Button btn = (Button) v;
        cal = btn.getText().toString();
        cal_con = true;
        result.setText(frist+cal);
    }

    public void OnCompare(View v)
    {
        int revalue;
        switch (cal)
        {
            case "+":
                revalue = Integer.parseInt(frist) + Integer.parseInt(second);
                result.setText(revalue+" ");
                break;
            case "-":
                revalue = Integer.parseInt(frist) - Integer.parseInt(second);
                result.setText(revalue+" ");
                break;
            case "/":
                revalue = Integer.parseInt(frist) / Integer.parseInt(second);
                result.setText(revalue+" ");
                break;
            case "*":
                revalue = Integer.parseInt(frist) * Integer.parseInt(second);
                result.setText(revalue+" ");
                break;
        }
        frist = null;
        second = null;
        cal = null;
        cal_con =false;
    }

    public void OnNull(View v)
    {
        input1.setText("");
        input2.setText("");
        result.setText("");
        frist = null;
        second = null;
        cal = null;
        cal_con =false;
    }

}