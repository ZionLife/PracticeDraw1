package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        int width = getWidth();
        int height = getHeight();

        float centerX1 = width * (1 / 4f);
        float centerX2 = width * (3 / 4f);
        float centerY1 = height * (1 / 4f);
        float centerY2 = height * (3 / 4f);
        float radius = (height - 10) / 4f;

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.BLACK);
        canvas.drawCircle(centerX1, centerY1, radius, paint1);

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.BLACK);
        canvas.drawCircle(centerX2, centerY1, radius, paint2);

        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.BLUE);
        canvas.drawCircle(centerX1, centerY2, radius, paint3);

        Paint paint4 = new Paint();
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(20);
        paint4.setColor(Color.BLACK);
        canvas.drawCircle(centerX2, centerY2, radius - 20, paint4);
    }
}
