package com.brain.jd.fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.brain.jd.controller.JDBaseController;
import com.brain.jd.listenner.IContrllerListenner;

/**
 * Fragment 基类
 * @author : Brian
 * @date : 2017/6/23
 */

public abstract class JDBaseFragment extends Fragment implements IContrllerListenner {

    /**
     * 控制器对象
     */
    protected JDBaseController mController;

    /**
     * 消息处理
     */
    protected Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JDBaseFragment.this.handleMessage(msg);
        }
    };

    /**
     * 消息处理
     * @param msg 消息
     */
    protected void handleMessage(Message msg){
        // default empty implement
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

}
