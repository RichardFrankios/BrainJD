package com.brain.jd.consts;

/**
 * 网络相关的常量
 * @author : Brian
 * @date : 2017/6/22
 */

public interface INetWorkConst {
    /**
     * 服务器地址
     */
    public static final String BASE_URL = "http://mall.520it.com";

    /**
     * 登录接口, 地址
     */
    public static final String LOGIN_URL = BASE_URL + "/login";
    /**
     * 注册接口
     */
    public static final String REGISTER_URL = BASE_URL + "/regist";
    /**
     * 重置密码
     */
    public static final String RESET_URL = BASE_URL + "/reset";
    /**
     * banner url
     */
    public static final String BANNER_URL = BASE_URL + "/banner";
    /**
     * Second kill
     */
    public static final String SECOND_KILL_URL = BASE_URL + "/seckill";
    /**
     * get you fav
     */
    public static final String SECOND_RECOMMEND_URL = BASE_URL + "/getYourFav";
}
