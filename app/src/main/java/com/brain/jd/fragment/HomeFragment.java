package com.brain.jd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.brain.jd.R;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.HomeController;
import com.brain.jd.domain.BannerBean;
import com.brain.jd.ui.HorizontalListView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页Fragment
 * @author : Brian
 * @date : 2017/6/23
 */

public class HomeFragment extends JDBaseFragment{

    private static final String TAG = "HomeFragment";
    private HorizontalListView mHlvSecondKill;
    private ViewPager mVpAd;
    private RelativeLayout mRlAd;

    /**
     * banner 数据集合
     */
//    private List<BannerBean> mBannerBeens = new ArrayList<>();
    private AdAdapter mAdAdpater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return View.inflate(getActivity(), R.layout.fragment_home, null);
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
    }

    @Override
    public void initController() {
        mController = new HomeController(getActivity());
        mController.setIContrllerListenner(this);
    }

    @Override
    public void initUI() {
        mHlvSecondKill = (HorizontalListView) getActivity().findViewById(R.id.horizon_listview);

        mRlAd = (RelativeLayout) getActivity().findViewById(R.id.ad_rl);
        mVpAd = (ViewPager) getActivity().findViewById(R.id.ad_vp);
        mAdAdpater = new AdAdapter();
//        mAdAdpater.setDatas(mBannerBeens);

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
        }
    }

    /**
     * 处理Banner
     */
    private void handleBannerResult(Object obj) {
        mRlAd.setVisibility(View.VISIBLE);
        mAdAdpater.setDatas((List<BannerBean>) obj);
        mAdAdpater.notifyDataSetChanged();
        mVpAd.setAdapter(mAdAdpater);
    }

    /**
     * 广告轮播适配器
     */
    class AdAdapter extends PagerAdapter {

        private List<BannerBean> mDatas = new ArrayList<>();

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
            Log.d(TAG, "instantiateItem: =======");
            BannerBean bannerBean = mDatas.get(position);

            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 加载图片
            Glide.with(HomeFragment.this)
                    .load(INetWorkConst.BASE_URL + bannerBean.getAdUrl());

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void setDatas(List<BannerBean> bannerBeens) {
            mDatas = bannerBeens;
            Log.d(TAG, "handleBannerResult: " + mDatas);
        }
    }







}
