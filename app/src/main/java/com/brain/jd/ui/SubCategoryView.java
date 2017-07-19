package com.brain.jd.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.brain.jd.domain.RTopCategoryBean;
import com.brain.jd.listenner.IViewContainer;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class SubCategoryView extends FlexiScrollView implements IViewContainer{

    private static final String TAG = "SubCategoryView";

    public SubCategoryView(Context context) {
        this(context, null);
    }

    public SubCategoryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubCategoryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onShow(Object... values) {
        RTopCategoryBean rTopCategoryBean = (RTopCategoryBean) values[0];
        Log.d(TAG, "onShow: " + rTopCategoryBean);
    }
}
