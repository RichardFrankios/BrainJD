package com.brain.jd.ui.pop_window;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.brain.jd.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * @author : Brian
 * @date : 2017/7/24
 */

public class ProductSortPopWindow extends IPopWindowProtocol {


    private ProductSortChangeListener mProductSortChangeListener;

    public void setProductSortChangeListener(ProductSortChangeListener productSortChangeListener) {
        mProductSortChangeListener = productSortChangeListener;
    }

    public ProductSortPopWindow(Context context) {
        super(context);
    }

    @Override
    public void initUi() {
        View convertView = LayoutInflater
                .from(mContext)
                .inflate(R.layout.product_sort_pop_view, null, false);
        x.view().inject(this, convertView);
        mPopupWindow = new PopupWindow(convertView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.update();
    }

    @Override
    public void onShow(View anchor) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, 0, 0);
        }
    }

    @Event({R.id.all_sort, R.id.new_sort, R.id.comment_sort})
    private void onClicked(View view) {
        int action = 0;
        switch (view.getId()) {
            case R.id.all_sort:
                action = 1;
                break;
            case R.id.new_sort:
                action = 2;
                break;
            case R.id.comment_sort:
                action = 3;
                break;
        }
        if (mProductSortChangeListener != null) {
            mProductSortChangeListener.onSortChanged(action);
        }
        onDismiss();
    }

    public interface ProductSortChangeListener {
        public void onSortChanged(int action);
    }

}
