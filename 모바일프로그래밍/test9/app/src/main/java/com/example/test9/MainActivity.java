package com.example.test9;

import android.os.Bundle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {

    private SurfaceView surfaceView;
    private Button startStopButton;

    private DrawThread drawThread;
    private Timer timer;

    private boolean isDrawing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        surfaceView = new SurfaceView(this);
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        frameLayout.addView(surfaceView);

        startStopButton = new Button(this);
        startStopButton.setText("Start");
        startStopButton.setOnClickListener(this);
        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.gravity = (android.view.Gravity.BOTTOM | android.view.Gravity.CENTER_HORIZONTAL);
        startStopButton.setLayoutParams(buttonParams);
        frameLayout.addView(startStopButton);

        setContentView(frameLayout);

        surfaceView.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Do nothing
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Do nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopDrawing();
    }

    @Override
    public void onClick(View v) {
        if (isDrawing) {
            stopDrawing();
            startStopButton.setText("Start");
        } else {
            startDrawing();
            startStopButton.setText("Stop");
        }
        isDrawing = !isDrawing;
    }

    private void startDrawing() {
        drawThread = new DrawThread();
        drawThread.start();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawRandomCircle();
            }
        }, 0, 1000); // Change this value to adjust the interval
    }

    private void stopDrawing() {
        if (drawThread != null) {
            drawThread.setRunning(false);
            try {
                drawThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drawThread = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void drawRandomCircle() {
        Canvas canvas = surfaceView.getHolder().lockCanvas();
        if (canvas != null) {
            try {
                canvas.drawColor(Color.WHITE);

                Random random = new Random();
                Paint paint = new Paint();
                for (int i = 0; i < 20; i++) {
                    int x = random.nextInt(canvas.getWidth());
                    int y = random.nextInt(canvas.getHeight());
                    int radius = random.nextInt(100);
                    paint.setColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                    canvas.drawCircle(x, y, radius, paint);
                }
            } finally {
                surfaceView.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    private class DrawThread extends Thread {
        private boolean isRunning = true;

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(1000 / 60); // Adjust this value to control frame rate
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}