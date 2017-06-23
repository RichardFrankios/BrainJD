package com.brain.jd.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.brain.jd.acitvity.JDBaseActivity;

/**
 * Activity 工具类
 * @author : Brian
 * @date : 2017/6/22
 */

public class ActivityUtils {

    /**
     * 启动新的Activity
     * @param ctx     上下文
     * @param aty     要启动的Activity
     * @param isFinishSelf 是否结束自身
     */
    public static void startActivity(Context ctx, Class<? extends JDBaseActivity> aty, boolean isFinishSelf) {
        ctx.startActivity(new Intent(ctx, aty));
        if (isFinishSelf)
            ((Activity)ctx).finish();
    }
}
