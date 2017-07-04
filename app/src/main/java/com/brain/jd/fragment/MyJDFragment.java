package com.brain.jd.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.jd.JDApplication;
import com.brain.jd.R;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.domain.RLoginResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * 主页Fragment
 * @author : Brian
 * @date : 2017/6/23
 */

public class MyJDFragment extends JDBaseFragment{


    private TextView mTvUserName;
    private TextView mTvWaitPay;
    private TextView mTvWaitReceivePay;
    private ImageView mIvIcon;
    private TextView mUserLevelTv;

    public static final String[] sUserLevelString = new String[]{
            "注册会员", "铜牌会员", "银牌会员", "金牌会员", "钻石会员"
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.fragment_mine, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        initData();
    }

    private void initData() {
        // 从Application中获取数据
        JDApplication application = (JDApplication) getActivity().getApplication();
        RLoginResult rLoginResult = application.mRLoginResult;
        // 设置界面数据
        if (rLoginResult != null) {
            mTvUserName.setText(rLoginResult.userName);
            mTvWaitPay.setText(rLoginResult.waitPayCount + "");
            mTvWaitReceivePay.setText(rLoginResult.waitReceiveCount + "");
            mUserLevelTv.setText(sUserLevelString[rLoginResult.userLevel - 1]);

            Glide.with(this)
                    .load(INetWorkConst.BASE_URL + rLoginResult.userIcon)
                    .asBitmap()                          // 注意必须使用这个, BitmapImageViewTarget才可以使用
                    .placeholder(R.drawable.home_jd)
                    .into(new BitmapImageViewTarget(mIvIcon){// 设置圆形图片
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable rbd = RoundedBitmapDrawableFactory.create(getResources(), resource);
                            rbd.setCircular(true);
                            mIvIcon.setImageDrawable(rbd);
                        }
                    });
        }
    }

    @Override
    public void initUI() {
        mTvUserName = (TextView) getActivity().findViewById(R.id.user_name_tv);
        mTvWaitPay = (TextView) getActivity().findViewById(R.id.wait_pay_tv);
        mTvWaitReceivePay = (TextView) getActivity().findViewById(R.id.wait_receive_tv);
        mIvIcon = (ImageView) getActivity().findViewById(R.id.user_icon_iv);
        mUserLevelTv =(TextView) getActivity().findViewById(R.id.user_level_tv);
    }
}
