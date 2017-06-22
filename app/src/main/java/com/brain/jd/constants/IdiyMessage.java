package com.brain.jd.constants;

/**
 * 自定i消息
 * @author : Brian
 * @date : 2017/6/22
 */

public interface IdiyMessage {
    /**
     * 登录请求
     */
    public static final int MSG_ACTION_LOGIN        = 1;
    /**
     * 登录请求返回
     */
    public static final int MSG_ACTION_LOGIN_RESULT = 2;

    /**
     * 注册消息
     */
    public static final int MSG_ACTION_REGISTER        = 3;
    public static final int MSG_ACTION_REGISTER_RESULT = 4;

    /**
     * 重置密码
     */
    public static final int MSG_ACTION_RESET        = 5;
    public static final int MSG_ACTION_RESET_RESULT = 6;




}