package com.brain.jd.controller;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.db.UserDao;
import com.brain.jd.domain.RResult;
import com.brain.jd.utils.NetWorkUtil;

import java.util.HashMap;

/**
 * @author : Brian
 * @date : 2017/6/22
 */

public class UserController extends JDBaseController {
    private static final String TAG = "UserController";
    private UserDao mUserDao;

    public UserController(Context context) {
        super(context);
        mUserDao = new UserDao(mContext);
    }


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
            case IdiyMessage.MSG_ACTION_SAVE_DB:
                doSave2Db((String) values[0],(String)values[1]);
                break;

            case IdiyMessage.MSG_ACTION_GET_DB:
                doGetFromDb();
                break;





        }
    }

    /**
     * 从数据库中获取用户信息
     */
    private void doGetFromDb() {
        UserDao.UserInfo userInfo = mUserDao.getUser();
        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_GET_DB_RESULT, userInfo);
        }
    }

    /**
     * 保存信息到数据库
     * @param username  用户名
     * @param pwd       密码
     */
    private void doSave2Db(String username, String pwd) {
        // 删除之前的信息
        mUserDao.clearUser();
        // 插入新信息
        boolean res = mUserDao.insertUser(username, pwd);
        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_SAVE_DB_RESULT, res);
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
                urlStr = INetWorkConst.LOGIN_URL;
                break;
            case IdiyMessage.MSG_ACTION_REGISTER_RESULT:
                urlStr = INetWorkConst.REGISTER_URL;
                break;
            case IdiyMessage.MSG_ACTION_RESET_RESULT:
                urlStr = INetWorkConst.RESET_URL;
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
