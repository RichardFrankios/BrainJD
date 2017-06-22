package com.brain.jd.acitvity;

import android.os.Bundle;

import com.brain.jd.R;
import com.brain.jd.listenner.IBottomBarListenner;
import com.brain.jd.ui.BottomBar;


public class MainActivity extends BaseActivity {

    private BottomBar mBbTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    @Override
    public void initUI() {
        mBbTabBar = (BottomBar) findViewById(R.id.bottom_bar);
        mBbTabBar.setIBottomBarListenner(new IBottomBarListenner() {
            @Override
            public void onTabBarItemClicked(int which) {
                tip("点击了 : " + which);
            }
        });

        mBbTabBar.setCurrentIndex(0);
    }
}
