package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {
    float[] rates = new float[]{0.2f, 0.1f, 0.5f, 0.3f, 0.7f, 0.4f, 1f}; //直方图数据
    String[] titles = new String[]{"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        int lengthX = getWidth() - 30 * 2;
        int lengthY = getHeight() - 30 * 2;

        int startX = 30;
        int startY = 30;
        Paint paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(startX, startY);
        path.rLineTo(0, lengthY);
        path.rLineTo(lengthX, 0);
        canvas.drawPath(path, paint1);

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.parseColor("#72B916"));
        paint2.setTextSize(10);

        //根据item数目与横坐标宽度计算出每个item占宽度
        float eachItemWidth = (lengthX - 20) / rates.length;
        int dividerWidth = (int) (eachItemWidth * (1 / 6f));
        int rectWidth = (int) (eachItemWidth * (5 / 6f));

        Paint paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        paint3.setTextAlign(Paint.Align.CENTER);
        paint3.setTextSize(15);

        float currX = startX + dividerWidth; //当前绘制的item的x坐标，每次+dividerWidth+rectWidth
        float currY = startY + lengthY; //当前绘制的item的y坐标，该坐标固定
        for (int i = 0; i < rates.length; i++) {
            canvas.drawRect(currX, currY - lengthY * rates[i], currX + rectWidth, currY, paint2);
            canvas.drawText(titles[i], currX + rectWidth / 2f, currY + 15, paint3);
            currX = currX + rectWidth + dividerWidth;
        }
    }
}
