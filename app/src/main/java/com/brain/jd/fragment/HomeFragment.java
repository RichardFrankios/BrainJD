package com.brain.jd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.brain.jd.R;
import com.brain.jd.adapter.SecondKillAdapter;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.HomeController;
import com.brain.jd.domain.BannerBean;
import com.brain.jd.domain.RSecondKillBean;
import com.brain.jd.ui.HorizontalListView;
import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主页Fragment
 * @author : Brian
 * @date : 2017/6/23
 */

public class HomeFragment extends JDBaseFragment{

    private static final String TAG = "HomeFragment";

    @ViewInject(R.id.horizon_listview)
    private HorizontalListView mHlvSecondKill;

    @ViewInject(R.id.ad_vp)
    private ViewPager mVpAd;

    @ViewInject(R.id.ad_rl)
    private RelativeLayout mRlAd;

    @ViewInject(R.id.ad_indicator)
    private LinearLayout mAdIndicators;
    // TopBannerAdapter
    private TopBannerAdAdapter mTopBannerAdAdpater;
    private Timer mTimer;
    private SecondKillAdapter mSecondKillAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        // 依赖注入
        x.view().inject(this, view);
        return view;
    }

    /**
     * 在这个方法中可以查找控件了
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initUI();

        initController();

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 1. 获取广告数据
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_BANNER,1);
        // 2. 获取SecondKill
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_SECOND_KILL,0);
    }

    @Override
    public void initController() {
        mController = new HomeController(getActivity());
        mController.setIContrllerListenner(this);
    }

    @Override
    public void initUI() {
        initTopAd();

        initSecondKill();
    }

    private void initSecondKill() {
        mSecondKillAdapter = new SecondKillAdapter(getActivity());
        mHlvSecondKill.setAdapter(mSecondKillAdapter);
    }

    private void initTopAd() {
        // 设置顶部轮播器
        mTopBannerAdAdpater = new TopBannerAdAdapter();
        mVpAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: ----------");
                Log.d(TAG, "onPageSelected: " + position);

                for (int i = 0; i < mAdIndicators.getChildCount(); i ++) {
                    mAdIndicators.getChildAt(i).setEnabled(position == i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.MSG_ACTION_BANNER_RESULT:
                handleBannerResult(msg.obj);
                break;
            case IdiyMessage.MSG_ACTION_SECOND_KILL_RESULT:
                handleSecondKillResult((List<RSecondKillBean>) msg.obj);
                break;

        }
    }

    /**
     * Deal second kill data
     */
    private void handleSecondKillResult(List<RSecondKillBean> obj) {
        mSecondKillAdapter.setDatas(obj);
        mSecondKillAdapter.notifyDataSetChanged();
    }

    /**
     * 处理Banner
     */
    private void handleBannerResult(Object obj) {
        mRlAd.setVisibility(View.VISIBLE);
        mTopBannerAdAdpater.setDatas((List<BannerBean>) obj);
        mTopBannerAdAdpater.notifyDataSetChanged();
        mVpAd.setAdapter(mTopBannerAdAdpater);
    }

    /**
     * Banner Adapter
     */
    class TopBannerAdAdapter extends PagerAdapter {

        private List<BannerBean> mDatas = new ArrayList<>();
        private List<ImageView> mImageViews = new ArrayList<>();

        @Override
        public int getCount() {
            return mDatas == null ? 0 : mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         * 设置数据
         */
        public void setDatas(List<BannerBean> bannerBeens) {
            // 使用SmartView
            mDatas = bannerBeens;
            // 初始化所有的布局
            for (int i = 0; i < bannerBeens.size(); i ++) {
                // 创建View
                mImageViews.add(createOneBanner(i));
                // 创建指示器
                mAdIndicators.addView(createOneIndicator(i));
            }

            // 初始化定时器.
            if (mTimer == null) {
                mTimer = new Timer();
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                scrollBanners();
                            }
                        });
                    }
                },0, 3000);
            }

        }

        /**
         * 创建一个Banner页面
         */
        @NonNull
        private ImageView createOneBanner(int index) {
            BannerBean bannerBean = mDatas.get(index);
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 加载图片
            Glide.with(HomeFragment.this)
                    .load(INetWorkConst.BASE_URL + bannerBean.getAdUrl())
                    .into(imageView);
            return imageView;
        }

        /**
         * 创建一个指示器
         */
        private View createOneIndicator(int index) {
            View view = new View(getActivity());
            view.setBackgroundResource(R.drawable.ad_indicator_bg);
            // 基于 720*1280 开发, 除以 2
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
            layoutParams.setMargins(10, 0, 0, 0);
            view.setLayoutParams(layoutParams);
            view.setEnabled(index == 0);
            return view;
        }
    }

    /**
     * 切换Banner
     */
    private void scrollBanners() {
        int currentItem = mVpAd.getCurrentItem();
        currentItem = (currentItem == mTopBannerAdAdpater.getCount()-1)? 0 : currentItem + 1 ;
        mVpAd.setCurrentItem(currentItem);
    }


}
