package com.brain.jd.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brain.jd.R;
import com.brain.jd.listenner.IBottomBarListenner;

/**
 * @author : Brian
 * @date : 2017/6/22
 */

public class BottomBar extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "BottomBar";

    /**
     * 监听者
     */
    private IBottomBarListenner mIBottomBarListenner;

    public void setIBottomBarListenner(IBottomBarListenner listenner) {
        mIBottomBarListenner = listenner;
    }

    private ImageView mIvMain;
    private TextView mTvMain;

    private ImageView mIvCategory;
    private TextView mTvCategory;

    private ImageView mIvShopcar;
    private TextView mTvShopcar;

    private ImageView mIvMine;
    private TextView mTvMine;


    /**
     * 当前item
     */
    private int mCurrentIndex = -1;



    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置当前item
     * @param index
     */
    public void setCurrentIndex(int index) {
        if (index > 3 || index < 0) {
            return;
        }
        selectItemProcess(index);
    }

    /**
     * 当measure和layout都执行完了, 就会执行这个方法.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 1. 设置点击使劲
        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);


        // 2. 获取到所有的控件
        mIvMain = (ImageView) findViewById(R.id.frag_main_iv);
        mTvMain = (TextView) findViewById(R.id.frag_main);

        mIvCategory = (ImageView) findViewById(R.id.frag_category_iv);
        mTvCategory = (TextView) findViewById(R.id.frag_category);

        mIvShopcar = (ImageView) findViewById(R.id.frag_shopcar_iv);
        mTvShopcar = (TextView) findViewById(R.id.frag_shopcar);

        mIvMine = (ImageView) findViewById(R.id.frag_mine_iv);
        mTvMine = (TextView) findViewById(R.id.frag_mine);

    }
    /**
     * 根据index更新UI,
     * @param index index
     * @return 如果点击的是当前item则不用更新
     */
    private boolean updateUiByIndex(int index) {

        if (index == mCurrentIndex)
            return false;

        mTvMain.setSelected(index == 0);
        mIvMain.setSelected(index == 0);

        mTvCategory.setSelected(index == 1);
        mIvCategory.setSelected(index == 1);

        mTvShopcar.setSelected(index == 2);
        mIvShopcar.setSelected(index == 2);

        mTvMine.setSelected(index == 3);
        mIvMine.setSelected(index == 3);

        mCurrentIndex = index;
        return true;
    }


    private int getIndexById(int click_id) {
        int res_id = 0;
        switch (click_id) {
            case R.id.frag_main_ll:
                res_id = 0;
                break;
            case R.id.frag_category_ll:
                res_id = 1;
                break;
            case R.id.frag_shopcar_ll:
                res_id = 2;
                break;
            case R.id.frag_mine_ll:
                res_id = 3;
                break;
        }
        return res_id;
    }

    @Override
    public void onClick(View v) {
        selectItemProcess(getIndexById(v.getId()));
    }

    /**
     * 更新item
     * @param index
     */
    private void selectItemProcess(int index) {
        if (updateUiByIndex(index) && (mIBottomBarListenner != null)) {
            Log.d(TAG, "onClick: -----------");
            mIBottomBarListenner.onTabBarItemClicked(index);
        }
    }
}
