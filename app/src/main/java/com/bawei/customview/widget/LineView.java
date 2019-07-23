package com.bawei.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.bawei.customview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create by ysn
 * TIME: 2019/7/23
 * Doing: 折线图
 */
public class LineView extends View{

    private Rect mSrcRect,mDestRect;

    private Paint xTextPaint;
    private Paint yTextPaint;

    private Paint xLinePaint;
    private Paint yLinePaint;

    private Paint bgPaint;
    private Paint dotPaint;

    private List<String> xList;
    private List<Point> pointList = new ArrayList<>();

    private Bitmap bitmap;

    public LineView(Context context) {
        this(context,null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    * 添加数据
    * 参数：x轴坐标的集合，y轴坐标的集合
    */
    public void setData(List<String> xList,List<String> yList){

        if (xList.size() != yList.size()){
            throw new RuntimeException("x轴坐标与y轴坐标要一样样的呢！");
        }
        this.xList = xList;
        invalidate();//刷新作用
    }

    private void initPaint() {
        //画线
        xLinePaint = new Paint();
        xLinePaint.setAntiAlias(true);
        xLinePaint.setStyle(Paint.Style.FILL);
        xLinePaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        xTextPaint = new Paint();
        xTextPaint.setAntiAlias(true);
        xTextPaint.setStyle(Paint.Style.FILL);
        xTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        xTextPaint.setTextSize(30);
        xTextPaint.setTextAlign(Paint.Align.CENTER);

        //画圆
        dotPaint = new Paint();
        dotPaint.setAntiAlias(true);
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));


        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        xList = new ArrayList<>();
        xList.add("7-22");
        xList.add("7-23");
        xList.add("7-24");
        xList.add("7-25");
        xList.add("7-26");
        xList.add("7-27");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画线
        canvas.drawLine(50,600,1000,600,xLinePaint);
        canvas.drawLine(50,600,50,50,xLinePaint);
        //平均分配
        int x = 950/7;
        //清除
        pointList.clear();
        //循环遍历
        for (String s : xList) {
            Point point = new Point();
            point.x = 50+x;
            point.y = new Random().nextInt(600);
            pointList.add(point);
            canvas.drawText(s,50+x,640,xTextPaint);
            x+=950/7;
        }

        for (int i = 0; i < pointList.size(); i++) {
            canvas.drawCircle(pointList.get(i).x,pointList.get(i).y,20,dotPaint);

            if (i<pointList.size()-1)
                canvas.drawLine(pointList.get(i).x,pointList.get(i).y,pointList.get(i+1).x,pointList.get(i+1).y,xLinePaint);
        }

    }

}
