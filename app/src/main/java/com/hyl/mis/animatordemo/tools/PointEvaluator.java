package com.hyl.mis.animatordemo.tools;

import android.animation.TypeEvaluator;

import com.hyl.mis.animatordemo.bean.Point;

import static android.R.attr.fraction;

/**
 * Created by mis on 2016/11/11.
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
