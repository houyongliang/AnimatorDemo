package com.hyl.mis.animatordemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_main_start)
    Button btMainStart;
    @BindView(R.id.bt_main_tegether)
    Button btMainTegether;
    @BindView(R.id.iv_main)
    ImageView ivMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化数据


    }

    private void initView() {
        ButterKnife.bind(this);
    }
}
