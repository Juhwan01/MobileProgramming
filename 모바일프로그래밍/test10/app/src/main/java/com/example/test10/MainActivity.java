package com.example.test10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int currentIndex = 0; // 현재 이미지 인덱스
    private Bitmap originalBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 이미지뷰 생성
        imageView = new ImageView(this);
        setContentView(imageView);

        // 이미지 리소스 가져오기
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.your_image);

        // 애니메이션 실행
        animateImages();
    }

    // 비트맵 자르기 메소드
    private Bitmap cropBitmap() {
        int width = originalBitmap.getWidth() / 5;
        int height = originalBitmap.getHeight() / 2;
        int startX = width * (currentIndex % 5); // 현재 이미지의 x 시작 위치
        int startY = height * (currentIndex / 5); // 현재 이미지의 y 시작 위치

        return Bitmap.createBitmap(originalBitmap, startX, startY, width, height);
    }

    // 애니메이션 실행 메소드
    private void animateImages() {
        final int delayMillis = 100; // 이미지 전환 간격(ms)

        // 이미지뷰에 첫 번째 이미지 설정
        imageView.setImageBitmap(cropBitmap());

        // 이미지 전환을 위한 Runnable 실행
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentIndex++;
                if (currentIndex >= 10) {
                    currentIndex = 0; // 이미지 인덱스 초기화
                }
                // 다음 이미지로 전환
                imageView.setImageBitmap(cropBitmap());
                // 다음 이미지 전환을 위해 재귀 호출
                imageView.postDelayed(this, delayMillis);
            }
        }, delayMillis);
    }

}