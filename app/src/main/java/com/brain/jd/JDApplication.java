package com.brain.jd;

import android.app.Application;

import com.brain.jd.domain.RLoginResult;

import org.xutils.x;

/**
 * @author : Brian
 * @date : 2017/6/23
 */

public class JDApplication extends Application {

    public RLoginResult mRLoginResult;



    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化xUtils
        x.Ext.init(this);
    }
}
