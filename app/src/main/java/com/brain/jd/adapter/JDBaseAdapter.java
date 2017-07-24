package com.brain.jd.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public abstract class JDBaseAdapter<T> extends BaseAdapter {

    protected List<T> mData = new ArrayList<>();
    protected final Context mContext;

    protected int mCurrentSelect  = -1;

    public void setCurrentSelect(int currentSelect) {
        mCurrentSelect = currentSelect;
    }


    public JDBaseAdapter(Context ctx) {
        mContext = ctx;
    }

    public void setDatas(List<T> data) {
        mData = data;
    }



    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
