package com.bawei.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bawei.customview.R;

/**
 * Create by ysn
 * TIME: 2019/7/23
 * Doing: 圆点
 */
public class DotView extends View{

    private Paint paint;
    private float x,y;

    /*
    * 生成的构造方法
    */
    public DotView(Context context) {
        this(context,null);
    }

    public DotView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //调用下面的init方法
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attributeSet){
        //创建画笔实例
        paint = new Paint();
        //设置paint的属性
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//图像抖动
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
    }

    /*
    * 重写onDraw方法
    */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔画圆
        canvas.drawCircle(x+100,y+100,100,paint);
    }

    /*
    * 重写消费事件
    */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX() - 100;
                y = event.getY() - 100;
                break;

            case MotionEvent.ACTION_MOVE:
                x = event.getX() - 100;
                y = event.getY() - 100;
                break;

            case MotionEvent.ACTION_UP:
                x = 0;
                y = 0;
                break;
        }
        postInvalidate();//刷新作用
        return true;
    }
}

