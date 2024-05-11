package com.example.lab9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Paint drawPaint = new Paint();
    public int type;
    public int type_shape = 3;
    private Path path = new Path();
    private Path select_path = new Path();
    private int paintColor = 0XFF00000;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Canvas bitmapCanvas;
    private Bitmap canvasBitmap;
    private Bitmap drawBitmap;
    private float fart_x_poit;
    private float fart_y_poit;
    private float end_x_poit;
    private float end_y_poit;
    private float area[][] = new float[2][2];
    public boolean tf = false;
    private ArrayList<Path> pathList = new ArrayList<>(); // 경로 배열

    public SingleTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        type = 0;
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(paintColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(10f);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

        drawBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(drawBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                switch (type) {
                    case 0:
                        path.moveTo(touchX, touchY);
                        tf = false;
                        break;
                    case 1:
                        fart_x_poit = touchX;
                        fart_y_poit = touchY;
                        tf = false;
                        break;
                    case 2:
                        fart_x_poit = touchX;
                        fart_y_poit = touchY;
                        break;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                switch (type) {
                    case 0:
                        path.lineTo(touchX, touchY);
                        break;
                    case 1:
                        break;
                    case 2:
                        if (tf) {
                            moveSelectedArea(touchX - fart_x_poit, touchY - fart_y_poit);
                            fart_x_poit = touchX;
                            fart_y_poit = touchY;
                            end_x_poit += touchX;
                            end_y_poit += touchY;
                        }
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                switch (type) {
                    case 0:
                        drawCanvas.drawPath(path, paint);
                        pathList.add(new Path(path));
                        path.reset();
                        break;
                    case 1:
                        end_x_poit = touchX;
                        end_y_poit = touchY;
                        int w = (int) Math.abs(end_x_poit - fart_x_poit);
                        int h = (int) Math.abs(end_y_poit - fart_y_poit);
                        switch (type_shape) {
                            case 0:
                                getTri(fart_x_poit, fart_y_poit, w, h);
                                break;
                            case 1:
                                getRect(fart_x_poit, fart_y_poit, w, h);
                                break;
                            case 2:
                                getLine(fart_x_poit,fart_y_poit,end_x_poit,end_y_poit);
                                break;
                            case 3:
                                getEllipse(fart_x_poit, fart_y_poit, w, h);
                                break;
                        }
                        break;
                    case 2:
                        System.out.println(tf);
                        if (tf != true) {
                            System.out.println("ff");
                            end_x_poit = touchX;
                            end_y_poit = touchY;
                            area[0][0] = fart_x_poit;
                            area[0][1] = fart_y_poit;
                            area[1][0] = end_x_poit;
                            area[1][1] = end_y_poit;
                            tf = true;
                            ArrayList<Path> pathsToRemove = new ArrayList<>();
                            RectF areaRect = new RectF(Math.min(fart_x_poit, end_x_poit),
                                    Math.min(fart_y_poit, end_y_poit),
                                    Math.max(fart_x_poit, end_x_poit),
                                    Math.max(fart_y_poit, end_y_poit));
                            for (Path p : pathList) {
                                RectF bounds = new RectF();
                                p.computeBounds(bounds, true);
                                if (!areaRect.contains(bounds)) {
                                    pathsToRemove.add(p);
                                }
                            }
                            pathList.removeAll(pathsToRemove);
                        } else {
                            area[0][0] = fart_x_poit;
                            area[0][1] = fart_y_poit;
                            area[1][0] = end_x_poit;
                            area[1][1] = end_y_poit;
                            drawCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                            // 저장된 경로 다시 그리기
                            for (Path p : pathList) {
                                drawCanvas.drawPath(p, drawPaint); // drawPaint를 사용하여 이미 그려진 부분의 색상을 유지합니다.
                            }
                            invalidate();
                        }
                        break;
                }
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor);
        paint.setColor(paintColor);
        // drawPaint 색상도 변경합니다.
        drawPaint.setColor(paintColor);
    }

    public void getTri(float x, float y, int width, int height) {
        Path trianglePath = new Path();
        trianglePath.moveTo(x, y + height);
        trianglePath.lineTo(x + width, y + height);
        trianglePath.lineTo(x + (width / 2), y);
        trianglePath.lineTo(x, y + height);
        drawCanvas.drawPath(trianglePath, paint); // 비트맵 캔버스에 그리도록 수정합니다.
        pathList.add(new Path(trianglePath)); // 삼각형 경로를 경로 배열에 추가
        invalidate();
    }

    public void getRect(float x, float y, int width, int height) {
        Path rectPath = new Path();
        rectPath.moveTo(x, y);
        rectPath.lineTo(x + width, y);
        rectPath.lineTo(x + width, y + height);
        rectPath.lineTo(x, y + height);
        rectPath.lineTo(x, y);
        drawCanvas.drawPath(rectPath, paint); // 비트맵 캔버스에 그리도록 수정합니다.
        pathList.add(new Path(rectPath)); // 사각형 경로를 경로 배열에 추가
        invalidate();
    }

    public void drawCircle(float centerX, float centerY, float radius) {
        Path circlePath = new Path();
        circlePath.addCircle(centerX, centerY, radius, Path.Direction.CW);
        drawCanvas.drawPath(circlePath, paint); // 비트맵 캔버스에 그리도록 수정합니다.
        pathList.add(new Path(circlePath)); // 원의 경로를 경로 배열에 추가
        invalidate();
    }

    public void getLine(float s_x, float s_y, float e_x, float e_y)
    {
        Path linePath = new Path();
        linePath.moveTo(s_x, s_y);
        linePath.lineTo(e_x, e_y);
        drawCanvas.drawPath(linePath, paint); // 비트맵 캔버스에 그리도록 수정합니다.
        pathList.add(new Path(linePath)); // 사각형 경로를 경로 배열에 추가
        invalidate();
    }
    public void getEllipse(float x, float y, int width, int height) {
        Path ellipsePath = new Path();
        RectF rect = new RectF(x, y, x + width, y + height);
        ellipsePath.addOval(rect, Path.Direction.CW);
        drawCanvas.drawPath(ellipsePath, paint); // 비트맵 캔버스에 그리도록 수정합니다.
        pathList.add(new Path(ellipsePath)); // 타원 경로를 경로 배열에 추가
        invalidate();
    }

    private void moveSelectedArea(float deltaX, float deltaY) {
        // 선택된 영역의 왼쪽 상단 좌표와 오른쪽 하단 좌표를 계산합니다.
        float left = Math.min(fart_x_poit, end_x_poit);
        float top = Math.min(fart_y_poit, end_y_poit);
        float right = Math.max(fart_x_poit, end_x_poit);
        float bottom = Math.max(fart_y_poit, end_y_poit);

        // 선택된 영역 내에 있는 도형들을 이동합니다.
        for (Path path : pathList) {
            RectF bounds = new RectF();
            path.computeBounds(bounds, true);

            // 도형의 왼쪽 상단 좌표와 오른쪽 하단 좌표를 계산합니다.
            float shapeLeft = bounds.left;
            float shapeTop = bounds.top;
            float shapeRight = bounds.right;
            float shapeBottom = bounds.bottom;

            // 도형이 선택된 영역과 교차하는지 확인합니다.
            boolean intersects = !(right < shapeLeft || left > shapeRight || bottom < shapeTop || top > shapeBottom);

            // 도형이 선택된 영역과 교차하는 경우에만 이동합니다.
            if (intersects) {
                path.offset(deltaX, deltaY);
            }
        }

        // 비트맵 캔버스를 지우고, 업데이트된 경로를 다시 그립니다.
        bitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (Path p : pathList) {
            bitmapCanvas.drawPath(p, drawPaint);
        }
        invalidate();
    }



    public void ClearCan() {
        drawCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        if (tf) {
            RectF areaRect = new RectF();
            areaRect.left = Math.min(fart_x_poit, end_x_poit);
            areaRect.top = Math.min(fart_y_poit, end_y_poit);
            areaRect.right = Math.max(fart_x_poit, end_x_poit);
            areaRect.bottom = Math.max(fart_y_poit, end_y_poit);

            ArrayList<Path> pathsToMove = new ArrayList<>();
            for (Path path : pathList) {
                if (isPathIntersectArea(path, areaRect)) {
                    pathsToMove.add(path);
                }
            }

            // 선택된 영역과 교차하는 모든 도형 이동
            moveSelectedArea(end_x_poit - fart_x_poit, end_y_poit - fart_y_poit, pathsToMove);

            // 비트맵 캔버스를 투명 상태로 지우고, 업데이트된 경로를 다시 그립니다.
            bitmapCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            for (Path p : pathList) {
                bitmapCanvas.drawPath(p, drawPaint);
            }
        } else {
            pathList.clear();
        }
        invalidate();
    }

    private void moveSelectedArea(float deltaX, float deltaY, ArrayList<Path> pathsToMove) {
        for (Path path : pathsToMove) {
            path.offset(deltaX, deltaY);
        }
    }

    private boolean isPathIntersectArea(Path path, RectF areaRect) {
        // 경로와 area의 교차 영역을 계산
        Path.Op op = Path.Op.INTERSECT;
        Path intersectPath = new Path();
        intersectPath.addRect(areaRect, Path.Direction.CW);
        intersectPath.op(path, op);
        // 교차 영역이 있는지 확인
        return !intersectPath.isEmpty();
    }
}
