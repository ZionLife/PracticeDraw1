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
import android.view.View;

public class Practice11PieChartView extends View {
    private float[] rates = new float[]{0.3f, 0.15f, 0.2f, 0.15f, 0.15f, 0.05f};
    private int[] colors = new int[]{Color.parseColor("#F44336"), Color.parseColor("#FFC107"), Color.parseColor("#9C27B0"),
            Color.parseColor("#9E9E9E"), Color.parseColor("#009688"), Color.parseColor("#2196F3")};
    String[] titles = new String[]{"Froyo", "ICS", "JB", "KitKat", "L", "M"};
    private Paint mPaint4Arc;
    private Paint mPaint4Line;
    private Paint mPaint4Text;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private float mFirstPix;
    private float mStartAngle;
    private float mTotalDividerAngle;
    private float mDividerAngle;
    private float mTotalAngle;
    private RectF mRectF;
    private float mLineLength1;
    private float mLineLength2;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inits();

    }

    private void inits() {
        initPaints();
        initArgs();
    }

    private void initPaints() {
        mPaint4Arc = new Paint();
        mPaint4Arc.setColor(Color.RED);
        mPaint4Arc.setStyle(Paint.Style.FILL);
        mPaint4Arc.setAntiAlias(true);

        mPaint4Line = new Paint();
        mPaint4Line.setColor(Color.WHITE);
        mPaint4Line.setStyle(Paint.Style.STROKE);
        mPaint4Line.setAntiAlias(true);

        mPaint4Text = new Paint();
        mPaint4Text.setColor(Color.WHITE);
        mPaint4Text.setAntiAlias(true);
        mPaint4Text.setTextSize(15);
    }

    private void initArgs() {

        //第一个扇形的偏移量
        mFirstPix = 8;
        //从-180°开始绘制
        mStartAngle = -180;
        //分配给divider的总的角度
        mTotalDividerAngle = 15;
        //每个divider的角度
        mDividerAngle = mTotalDividerAngle / rates.length;
        //剩余留给扇形的总角度
        mTotalAngle = 360 - mTotalDividerAngle;
        //第一段线的长度
        mLineLength1 = 10;
        //第二段线的长度
        mLineLength2 = 15;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        mCenterX = getWidth() / 2f;
        mCenterY = getHeight() / 2f;
        mRadius = (getHeight() - 50) / 2f;
        //扇形所属的圆的RectF
        mRectF = new RectF(mCenterX - mRadius, mCenterY - mRadius, mCenterX + mRadius, mCenterY + mRadius);

        float currAngle = mStartAngle;//当前item开始的角度
        float sweepAngle;
        float midAngle;//由于线的起点在扇形弧的中心点，故需要计算出扇形角度的一半，再判断线该往左还是往右画
        float cosRadius;
        float sinRadius;
        float lineStartX;//图例的线的起点横坐标
        float lineStartY;//图例的线的起点纵坐标
        float lineEndX;
        float lineEndY;

        for (int i = 0; i < rates.length; i++) {
            mPaint4Arc.setColor(colors[i]);
            sweepAngle = mTotalAngle * rates[i];

            //绘制图例（包含Text与Lines）
            midAngle = currAngle + sweepAngle / 2f;
            cosRadius = (float) (mRadius * Math.cos(midAngle * Math.PI / 180));
            sinRadius = (float) (mRadius * Math.sin(midAngle * Math.PI / 180));
            lineStartX = mCenterX + cosRadius;
            lineStartY = mCenterY + sinRadius;
            if (midAngle > -180 && midAngle <= -90) {
                //在圆的左上部分
                canvas.drawLine(lineStartX, lineStartY, lineStartX - mLineLength1, lineStartY - mLineLength1, mPaint4Line);
                canvas.drawLine(lineStartX - mLineLength1, lineStartY - mLineLength1,
                        lineStartX - mLineLength1 - mLineLength2, lineStartY - mLineLength1, mPaint4Line);
                lineEndX = lineStartX - mLineLength1 - mLineLength2 - 5;
                lineEndY = lineStartY - mLineLength1;
                mPaint4Text.setTextAlign(Paint.Align.RIGHT);
            } else if (midAngle > -90 && midAngle <= 0) {
                //在圆的右上部分
                canvas.drawLine(lineStartX, lineStartY, lineStartX + mLineLength1, lineStartY - mLineLength1, mPaint4Line);
                canvas.drawLine(lineStartX + mLineLength1, lineStartY - mLineLength1,
                        lineStartX + mLineLength1 + mLineLength2, lineStartY - mLineLength1, mPaint4Line);

                lineEndX = lineStartX + mLineLength1 + mLineLength2 + 5;
                lineEndY = lineStartY - mLineLength1;
                mPaint4Text.setTextAlign(Paint.Align.LEFT);
            } else if (midAngle > 0 && midAngle <= 90) {
                //在圆的右下部分
                canvas.drawLine(lineStartX, lineStartY, lineStartX + mLineLength1, lineStartY + mLineLength1, mPaint4Line);
                canvas.drawLine(lineStartX + mLineLength1, lineStartY + mLineLength1,
                        lineStartX + mLineLength1 + mLineLength1, lineStartY + mLineLength1, mPaint4Line);
                lineEndX = lineStartX + mLineLength1 + mLineLength1 + 5;
                lineEndY = lineStartY + mLineLength1;
                mPaint4Text.setTextAlign(Paint.Align.LEFT);
            } else {
                //在圆的左下部分
                canvas.drawLine(lineStartX, lineStartY, lineStartX - mLineLength1, lineStartY + mLineLength1, mPaint4Line);
                canvas.drawLine(lineStartX - mLineLength1, lineStartY + mLineLength1,
                        lineStartX - mLineLength1 - mLineLength1, lineStartY + mLineLength1, mPaint4Line);

                lineEndX = lineStartX - mLineLength1 - mLineLength1 - 5;
                lineEndY = lineStartY + mLineLength1;
                mPaint4Text.setTextAlign(Paint.Align.RIGHT);
            }
            canvas.drawText(titles[i], lineEndX, lineEndY, mPaint4Text);

            //绘制扇形
            if (i == 0) {
                //第一个，让它突出来
                canvas.drawArc(mRectF.left - mFirstPix, mRectF.top - mFirstPix,
                        mRectF.right - mFirstPix, mRectF.bottom - mFirstPix, currAngle,
                        sweepAngle, true, mPaint4Arc);
            } else {
                canvas.drawArc(mRectF, currAngle, sweepAngle, true, mPaint4Arc);
            }

            currAngle += (mTotalAngle * rates[i] + mDividerAngle);
        }
    }
}
