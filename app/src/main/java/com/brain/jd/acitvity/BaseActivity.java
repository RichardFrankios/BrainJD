package com.brain.jd.acitvity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.brain.jd.controller.BaseController;
import com.brain.jd.listenner.IContrllerListenner;

/**
 * Activity 基类
 * @author : Brian
 * @date : 2017/6/22
 */

public abstract class BaseActivity extends FragmentActivity implements IContrllerListenner {


    /**
     * 控制器对象
     */
    protected BaseController mController;

    /**
     * 消息处理
     */
    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseActivity.this.handleMessage(msg);
        }
    };

    /**
     * 消息处理
     * @param msg 消息
     */
    protected void handleMessage(Message msg){
        // default empty implement
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 初始化界面的方法, 有子类实现
     */
    public abstract void initUI();

    /**
     * 初始化控制器
     */
    public void initController(){
        // default empty implement
    }
    /**
     * 动作直接结束
     * @param action 动作
     * @param values 结果
     */
    @Override
    public void onMessageResult(int action, Object... values) {
        // default empty implement
    }


    /**
     * 提示信息
     * @param txt
     */
    protected void tip(String txt) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }





}
