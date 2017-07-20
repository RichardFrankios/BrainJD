package com.brain.jd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : Brian
 * @date : 2017/7/20
 */

public class AutoBreakViewGroup extends ViewGroup implements View.OnClickListener{

    private static final String TAG = "AutoBreakViewGroup";

    private OnItemClickListener mOnItemClickListener;

    /**
     * 列数.
     */
    private int mColumn = 3;
    /**
     * 水平间距
     */
    private int mVerticalSpace = 4;

    /**
     * 垂直间距
     */
    private int mHorizontalSpace = 4;



    public AutoBreakViewGroup(Context context) {
        this(context, null);
    }

    public AutoBreakViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoBreakViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setColumn(int column) {
        mColumn = column;
    }

    public void setHorizontalSpace(int horizontalSpace) {
        mHorizontalSpace = horizontalSpace;
        // 计算View宽度
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childWidth = calChildWidth();
        int childCount = getChildCount();
        int colIndex = 0;
        int rowIndex = 0;
        int left = 0;
        int top = 0;

        for (int i = 0; i < childCount; i++) {
            colIndex = i%mColumn;
            rowIndex = i/mColumn;
            left = (colIndex * (mHorizontalSpace + childWidth) + mHorizontalSpace);
            top = (rowIndex * (mVerticalSpace + childWidth) + mVerticalSpace);
            View view = getChildAt(i);
            view.layout(left, top, left + childWidth, top + childWidth);
            view.setOnClickListener(this);
            view.setTag(i);
        }
    }

    /**
     * calculate child width
     */
    private int calChildWidth() {
        int childCount = getChildCount();
        int width = getMeasuredWidth();
        int space = (mColumn+1) * mHorizontalSpace;
        return (width - space) / mColumn;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }


    public interface OnItemClickListener {

        /**
         * 点击事件
         */
        void onItemClick(View view, int position);
    }


}
