package com.brain.jd.acitvity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.brain.jd.R;
import com.brain.jd.fragment.CategoryFragment;
import com.brain.jd.fragment.HomeFragment;
import com.brain.jd.fragment.JDBaseFragment;
import com.brain.jd.fragment.MyJDFragment;
import com.brain.jd.fragment.ShopcarFragment;
import com.brain.jd.listenner.IBottomBarListenner;
import com.brain.jd.ui.BottomBar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends JDBaseActivity {


    private static final String TAG = "MainActivity";

    private BottomBar mBbTabBar;

    /**
     * Fragment 标签
     */
    private static final String[] sFragmentTags = new String[]{
            "home_fragment","category_fragment","shopcar_fragment","myjd_fragment"};
    private FragmentManager mFragmentManager;

    /**
     * Fragment 集合
     */
    private List<JDBaseFragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();

        initUI();
    }

    @Override
    public void initUI() {
        mBbTabBar = (BottomBar) findViewById(R.id.bottom_bar);
        mBbTabBar.setIBottomBarListenner(new IBottomBarListenner() {
            @Override
            public void onTabBarItemClicked(int which) {
                handleTabItemClick(which);
            }
        });
        mBbTabBar.setCurrentIndex(0);
    }

    /**
     * 处理Tab的点击
     */
    private void handleTabItemClick(int which) {
        // 1. 显示Fragment, 切换Fragment
        switchFragmentByIndex(which);
    }

    /**
     * 切换Fragment
     */
    private void switchFragmentByIndex(int index) {
        Log.d(TAG, "switchFragmentByIndex: " + index);
        // 1. 创建Fragment
        JDBaseFragment fragment = (JDBaseFragment) mFragmentManager.findFragmentByTag(sFragmentTags[index]);
        if (fragment == null) {
            // 创建新的Fragment
            fragment = createFragmentByIndex(index);
        }
        // 2. 设置显示Fragment
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        for (JDBaseFragment frag : mFragments) {
            ft.hide(frag);
        }
        ft.show(fragment).commit();
    }

    /**
     * 创建新的Fragment
     */
    private JDBaseFragment createFragmentByIndex(int index) {
        Log.d(TAG, "创建新的Fragment : " + index);
        JDBaseFragment fragment = null;
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        switch (index) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new CategoryFragment();
                break;
            case 2:
                fragment = new ShopcarFragment();
                break;
            case 3:
                fragment = new MyJDFragment();
                break;
        }
        ft.add(R.id.top_bar, fragment,sFragmentTags[index]).commit();
        mFragments.add(fragment);
        return fragment;
    }
}
