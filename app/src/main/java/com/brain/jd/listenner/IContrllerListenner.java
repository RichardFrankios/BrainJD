package com.brain.jd.listenner;

/**
 * 控制器监听类
 * @author : Brian
 * @date : 2017/6/22
 */

public interface IContrllerListenner {

    /**
     * 消息执行结果
     * @param action 动作
     * @param values 结果
     */
    public void onMessageResult(int action, Object... values);

}
