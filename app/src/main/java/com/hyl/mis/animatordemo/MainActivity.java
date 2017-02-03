package com.hyl.mis.animatordemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hyl.mis.animatordemo.view.MyAnimView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       MyAnimView mav= (MyAnimView) findViewById(R.id.mav);
        mav.SetBallClick(new MyAnimView.BallOnClick() {
            @Override
            public void onClick() {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        mav.getListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });





    }
}
