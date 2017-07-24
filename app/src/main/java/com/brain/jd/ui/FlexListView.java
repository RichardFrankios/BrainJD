package com.brain.jd.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

public class FlexListView extends ListView{
    //初始可拉动Y轴方向距离
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 100;
    private Context mContext;
    //实际可上下拉动Y轴上的距离
    private int mMaxYOverscrollDistance;

    public FlexListView(Context context){
        super(context);
        mContext = context;
        initBounceListView();
    }

    public FlexListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBounceListView();
    }

    public FlexListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
        mContext = context;  
        initBounceListView();  
    }  
      
    private void initBounceListView(){  
        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();  
            final float density = metrics.density;  
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);  
    }
    //实现的本质就是在这里动态改变了maxOverScrollY的值
    @Override  
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,   
            int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {   
        return super.overScrollBy(deltaX, deltaY,
                scrollX, scrollY,
                scrollRangeX, scrollRangeY,
                maxOverScrollX, mMaxYOverscrollDistance,
                isTouchEvent);
    }  
      
}  