package com.example.xuan59.autonumber;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create -- xuan59
 * Date   -- 2018/12/11
 * Call me Alai
 */

public class AutoNumberView extends View {

    private int strokeColor;

    private int autoSpeed;

    private int textColor;

    private Paint paint;

    private Paint textPaint;

    private int centerX, centerY;

    private int radius;

    private ValueAnimator valueAnimator;

    private float value;

    private int number;

    private Rect targetRect;

    private int baseline;

    public AutoNumberView(Context context) {
        super(context);
    }

    public AutoNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoNumberView);
        strokeColor = typedArray.getColor(R.styleable.AutoNumberView_stroke_color, context.getResources().getColor(R.color.colorPrimaryDark));
        autoSpeed = typedArray.getInteger(R.styleable.AutoNumberView_auto_speed, 1000);
        textColor = typedArray.getColor(R.styleable.AutoNumberView_text_color, context.getResources().getColor(R.color.black));
        typedArray.recycle();
        init();
        initAnimation();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int min = Math.min(w, h);
        centerX = w / 2;
        centerY = h / 2;
        radius = (int) (min * 0.8f / 2);

        textPaint.setTextSize(radius / 2);
        targetRect = new Rect(-min / 2, -min / 2, min / 2, min / 2);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
    }

    private void init() {
        paint = new Paint();
        paint.setColor(strokeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

    }

    private void initAnimation() {
        valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(autoSpeed);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }



    public void setNumber(int number) {
        this.number = number;
    }

    public void setAutoSpeed(int autoSpeed) {
        valueAnimator.setDuration(autoSpeed);
    }

    public void startAnimation() {
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.drawText(String.valueOf((int)(value * number)), targetRect.centerX(), baseline, textPaint);
    }
}
