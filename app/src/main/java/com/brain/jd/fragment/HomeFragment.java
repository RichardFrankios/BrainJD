package com.brain.jd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brain.jd.R;

/**
 * 主页Fragment
 * @author : Brian
 * @date : 2017/6/23
 */

public class HomeFragment extends JDBaseFragment{

    private static final String TAG = "HomeFragment";

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
    }

    @Override
    public void initUI() {

    }
}
