package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        float width = getWidth();
        float height = getHeight();

        float radius = 50; //圆的半径
        float centerX1 = radius;
        float centerY = radius;
        float centerX2 = radius * 3;

        double angle = Math.PI / 6d;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        Path path = new Path();
        RectF rectF1 = new RectF(0, 0, radius * 2, radius * 2);
        path.addArc(rectF1, 150, 225);
        RectF rectF2 = new RectF(radius * 2, 0, radius * 4, radius * 2);
        path.arcTo(rectF2, 180, 225, false);
        path.lineTo(2 * radius, 2 * radius + (float) (radius / Math.tan(angle)));
        path.close();
        canvas.drawPath(path, paint);
    }
}
