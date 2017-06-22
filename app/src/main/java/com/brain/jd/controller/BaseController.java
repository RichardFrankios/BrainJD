package com.brain.jd.controller;

import com.brain.jd.listenner.IContrllerListenner;

/**
 * 控制器基类
 * @author : Brian
 * @date : 2017/6/22
 */

public abstract class BaseController {


    /**
     * 控制器监听类
     */
    protected IContrllerListenner mIContrllerListenner;

    /**
     * 设置监听器
     * @param listenner 监听器对象.
     */
    public void setIContrllerListenner(IContrllerListenner listenner) {
        mIContrllerListenner = listenner;
    }


    /**
     * 发送异步消息
     * @param action 动作名称
     * @param values 参数
     */
    public void sendAsyncMessage(final int action, final Object... values) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 开启新线程处理消息
                handleAsyncMessage(action, values);
            }
        }).start();
    }

    /**
     * 具体消息如何处理,有子类实现
     * @param pAction
     * @param pValues
     */
    public abstract void handleAsyncMessage(int pAction, Object[] pValues) ;



}
