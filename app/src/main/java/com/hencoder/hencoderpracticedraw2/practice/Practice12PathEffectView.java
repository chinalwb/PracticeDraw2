package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

//        Path myPath = new Path();
//        myPath.moveTo(80, 80);
//        myPath.rLineTo(-30, -40);
//        myPath.rLineTo(-30, 40);
//        myPath.close();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.RED);
//        canvas.drawPath(myPath, paint);


        // 第一处：CornerPathEffect
        canvas.save();
        paint.setPathEffect(new CornerPathEffect(35));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 0);
        // 第二处：DiscretePathEffect
        paint.setPathEffect(new DiscretePathEffect(20, 5));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        paint.setPathEffect(new DashPathEffect(new float[]{80, 20, 80, 20}, 0));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        Path dashPath = new Path();
        dashPath.rLineTo(-3*5,-4*5);
        dashPath.rLineTo(-3*5, 4*5);
        dashPath.close();
        paint.setPathEffect(new PathDashPathEffect(
                dashPath, 40, 0, PathDashPathEffect.Style.MORPH));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect
        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        paint.setPathEffect(new SumPathEffect(dashEffect, discreteEffect));
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // 第六处：ComposePathEffect
        paint.setPathEffect(new ComposePathEffect(dashEffect, discreteEffect));
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
