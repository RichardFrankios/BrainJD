package com.brain.jd.ui.pop_window;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author : Brian
 * @date : 2017/7/24
 */

public abstract class IPopWindowProtocol {

    private static final String TAG = "IPopWindowProtocol";

    protected PopupWindow mPopupWindow;

    protected Context mContext;

    public IPopWindowProtocol(Context context) {
        mContext = context;
        initUi();
    }

    public abstract void initUi();
    public abstract void onShow(View anchor);

    public void onDismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

}
