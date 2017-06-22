package com.brain.jd.controller;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.brain.jd.constants.INetWorkConstant;
import com.brain.jd.constants.IdiyMessage;
import com.brain.jd.domain.RResult;
import com.brain.jd.utils.NetWorkUtil;

import java.util.HashMap;

/**
 * @author : Brian
 * @date : 2017/6/22
 */

public class UserController extends BaseController {
    private static final String TAG = "UserController";



    @Override
    public void handleAsyncMessage(int action, Object[] values) {
        switch (action) {
            case IdiyMessage.MSG_ACTION_LOGIN:
                // 登录
                doLogin((String) values[0], (String) values[1]);
                break;

            case IdiyMessage.MSG_ACTION_REGISTER:
                // 注册
                doRegister((String) values[0], (String) values[1]);
                break;
            case IdiyMessage.MSG_ACTION_RESET:
                doReset((String) values[0]);
                break;


        }
    }

    /**
     * 重置密码
     * @param username 用户名
     */
    private void doReset(String username) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        callServer(params, IdiyMessage.MSG_ACTION_RESET_RESULT);
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param pwd      密码
     */
    private void doRegister(String username, String pwd) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("pwd", pwd);
        callServer(params, IdiyMessage.MSG_ACTION_REGISTER_RESULT);
    }

    /**
     * 做登录操作
     * @param username 用户名
     * @param pwd      密码
     */
    private void doLogin(String username, String pwd) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("pwd", pwd);
        callServer(params, IdiyMessage.MSG_ACTION_LOGIN_RESULT);
    }

    /**
     * 登录/注册
     * @param params        参数
     * @param diyMegResType 类型
     */
    private void callServer(HashMap<String, String> params, int diyMegResType) {

        String urlStr = "";
        switch (diyMegResType) {
            case IdiyMessage.MSG_ACTION_LOGIN_RESULT:
                urlStr = INetWorkConstant.LOGIN_URL;
                break;
            case IdiyMessage.MSG_ACTION_REGISTER_RESULT:
                urlStr = INetWorkConstant.REGISTER_URL;
                break;
            case IdiyMessage.MSG_ACTION_RESET_RESULT:
                urlStr = INetWorkConstant.RESET_URL;
                break;
        }
        Log.d(TAG, "url == " + urlStr);
        String json = NetWorkUtil.doPost(urlStr, params);
        Log.d(TAG, "callServer: " + json);

        // 2. 使用FastJSON解析数据. FastJSON不能解析嵌套数据
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(diyMegResType, rResult);
        }
    }
}
