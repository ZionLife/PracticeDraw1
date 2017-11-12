package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        float dx = getWidth() / 4;
        float dy = getHeight() / 4;
        RectF rectf = new RectF();
        rectf.left = dx * 1;
        rectf.top = dy * 1;
        rectf.right = dx * 3;
        rectf.bottom = dy * 3;

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectf, 180, 60, false, paint1);

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectf, 245, 100, true, paint2);

        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectf, 15, 150, false, paint3);
    }
}
