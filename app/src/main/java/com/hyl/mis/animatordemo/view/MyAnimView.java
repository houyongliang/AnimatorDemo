package com.hyl.mis.animatordemo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;

import com.hyl.mis.animatordemo.Main2Activity;
import com.hyl.mis.animatordemo.MainActivity;
import com.hyl.mis.animatordemo.bean.Point;
import com.hyl.mis.animatordemo.tools.PointEvaluator;

import static android.R.attr.animation;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by mis on 2016/11/11.
 */

public class MyAnimView extends View {
    private  Animator.AnimatorListener listener;
    public static final float RADIUS = 50f;
    private Point currentPoint;
    private Paint mPaint;
    private BallOnClick ballCall;
    private Boolean onBall;
    private float x;
    private float y;

    public void getListener(Animator.AnimatorListener listener){
        this.listener=listener;
    }

    public MyAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }
    public void SetBallClick(BallOnClick ballCall){
        this.ballCall=ballCall;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(currentPoint==null){
            currentPoint=new Point(RADIUS,RADIUS);//初始化圆形位置
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }

        super.onDraw(canvas);
    }

    private void startAnimation() {
        Point startPoint=new Point(RADIUS,RADIUS);//初始位置
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(5000).start();
        anim.addListener(listener);
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }
    public Boolean isOnBall(float x2,float y2){
        float x = currentPoint.getX();
        float y = currentPoint.getY();
//用球平方根的方法计算手指是否落在小球上
        float sqrt = (float) Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y));
        if (sqrt<=RADIUS) {//如果得到的点，小于等于半径就说明落在了小球上
            return true;
        }
        return false;//否则为true
    }
    public interface  BallOnClick{
        public void onClick();
    }

    public boolean onTouchEvent(MotionEvent event) {
// TODO Auto-generated method stub

        switch (event.getAction()) {
//按下的时候
            case MotionEvent.ACTION_DOWN:
//得到当前的坐标点
                x = event.getX();
                y = event.getY();
//判断鼠标是不是在球 ，如果不用这个方法的话就会出现，点击屏幕的任何位置小球 就会过去

                onBall = isOnBall(x, y);
//Toast.makeText(getContext(), onBall+"", 0).show();
                break;
            case MotionEvent.ACTION_MOVE:
                //位true的时候，才可以移动
                if (onBall) {
//位true的时候从新给小球设置坐标点，刷新一下就ok
                    x = (float) event.getX();
                    y = (float) event.getY();
//这个方法可以从新调用onDraw
//                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (onBall) {

                    x = (float) event.getX();
                    y = (float) event.getY();
                    postInvalidate();
                    ballCall.onClick();

                }
                break;

            default:
                break;
        }

        return true;
    }
}
