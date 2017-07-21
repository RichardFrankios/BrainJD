package com.brain.jd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author : Brian
 * @date : 2017/7/20
 */

public class AutoBreakViewGroup extends FrameLayout implements View.OnClickListener{

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
    private int mChildWidth;
    private int mChildCount;
    private int mChildHeight;

    private int mDeltaY = 40;

    private int mCurrentId = 0;

    public void setCurrentId(int id) {
        mCurrentId = id;
    }

    public void setDeltaY(int deltaY) {
        mDeltaY = deltaY;
    }

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 在此处设置子View的尺寸
        mChildWidth = calChildWidth();
        mChildCount = getChildCount();
        mChildHeight = mChildWidth + mDeltaY;
        // 设置尺寸
        int totalHeight = (mChildCount%3 == 0 ? mChildCount/3 : mChildCount/3 + 1)*(mChildHeight + mVerticalSpace);
        setMeasuredDimension(widthMeasureSpec, totalHeight);

        for (int i = 0; i < mChildCount; i++) {
            View view = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = mChildWidth;
            layoutParams.height = mChildHeight;
            view.setLayoutParams(layoutParams);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int totalHeight = (mChildCount%3 == 0 ? mChildCount/3 : mChildCount/3 + 1)*(mChildHeight + mVerticalSpace);
//        Log.d(TAG, "onLayout: " + totalHeight);
        // 此处使用的MeasureXX
//        super.onLayout(changed, l, t, r, t);

        int colIndex = 0;
        int rowIndex = 0;
        int left = 0;
        int top = 0;

        for (int i = 0; i < mChildCount; i++) {
            colIndex = i%mColumn;
            rowIndex = i/mColumn;
            left = (colIndex * (mHorizontalSpace + mChildWidth) + mHorizontalSpace);
            top = (rowIndex * (mVerticalSpace + mChildHeight) + mVerticalSpace);
            View view = getChildAt(i);
            view.layout(left, top, left + mChildWidth, top + mChildHeight);

            view.setOnClickListener(this);
            view.setTag(i);
        }
    }

    /**
     * calculate child width
     */
    private int calChildWidth() {
        int width = getMeasuredWidth();
        int space = (mColumn+1) * mHorizontalSpace;
        return (width - space) / mColumn;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag(), mCurrentId);
        }
    }


    public interface OnItemClickListener {

        /**
         * 点击事件
         */
        void onItemClick(View view, int position, int parentId);
    }


}
