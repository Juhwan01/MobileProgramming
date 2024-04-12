package com.example.test4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button kor_button = (Button) findViewById(R.id.btn_kor);
        Button jpn_button = (Button) findViewById(R.id.btn_japan);
        Button chi_button = (Button) findViewById(R.id.btn_china);
        ImageView kor_imageView = (ImageView) findViewById(R.id.img_kor);
        ImageView jpn_imageView = (ImageView) findViewById(R.id.img_jpn);
        ImageView chi_imageView = (ImageView) findViewById(R.id.img_chi);
        TextView kor_textView = (TextView) findViewById(R.id.text_kor);
        TextView jpn_textView = (TextView) findViewById(R.id.text_jpn);
        TextView chi_textView = (TextView) findViewById(R.id.text_chi);

        kor_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                kor_imageView.setVisibility(View.VISIBLE);
                kor_textView.setVisibility((View.VISIBLE));
                jpn_imageView.setVisibility(View.INVISIBLE);
                jpn_textView.setVisibility((View.INVISIBLE));
                chi_imageView.setVisibility(View.INVISIBLE);
                chi_textView.setVisibility((View.INVISIBLE));
            }
        });
        jpn_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                kor_imageView.setVisibility(View.INVISIBLE);
                kor_textView.setVisibility(View.INVISIBLE);
                jpn_imageView.setVisibility(View.VISIBLE);
                jpn_textView.setVisibility(View.VISIBLE);
                chi_imageView.setVisibility(View.INVISIBLE);
                chi_textView.setVisibility(View.INVISIBLE);
            }
        });
        chi_button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                kor_imageView.setVisibility(View.INVISIBLE);
                kor_textView.setVisibility(View.INVISIBLE);
                jpn_imageView.setVisibility(View.INVISIBLE);
                jpn_textView.setVisibility(View.INVISIBLE);
                chi_imageView.setVisibility(View.VISIBLE);
                chi_textView.setVisibility(View.VISIBLE);
            }
        });
    }
}