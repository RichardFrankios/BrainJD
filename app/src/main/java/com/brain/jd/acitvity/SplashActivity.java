package com.brain.jd.acitvity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.brain.jd.R;
import com.brain.jd.utils.ActivityUtils;


public class SplashActivity extends BaseActivity {

    private ImageView mIvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initUI();
        // 初始化动画
        initAlphaAnim();
    }

    @Override
    public void initUI() {
        mIvLogo = (ImageView) findViewById(R.id.logo_iv);
    }

    /**
     * 初始化透明度动画
     */
    private void initAlphaAnim() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 启动登录界面
                ActivityUtils.startActivity(SplashActivity.this, LoginActivity.class, true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // 开启动画
        mIvLogo.startAnimation(alphaAnimation);
    }
}
