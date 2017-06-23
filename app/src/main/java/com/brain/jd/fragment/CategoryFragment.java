package com.brain.jd.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 主页Fragment
 * @author : Brian
 * @date : 2017/6/23
 */

public class CategoryFragment extends JDBaseFragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        textView.setText("我是分类");
        return textView;
    }

    @Override
    public void initUI() {

    }
}
