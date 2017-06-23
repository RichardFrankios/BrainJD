package com.brain.jd.acitvity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.brain.jd.JDApplication;
import com.brain.jd.R;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.UserController;
import com.brain.jd.db.UserDao;
import com.brain.jd.domain.RLoginResult;
import com.brain.jd.domain.RResult;
import com.brain.jd.utils.ActivityUtils;


/**
 * 登录界面, 110 123456
 *
 * @author : Brian
 * @date : 2017/6/22
 */

public class LoginActivity extends JDBaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private TextView mEtName;
    private TextView mEtPwd;
    private Button mBtnLogin;
    private Button mRegisterBtn;
    private Button mBtnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        initController();

        initData();

    }

    private void initData() {
        // 从数据库中获取数据
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_GET_DB, 0);
    }

    @Override
    public void initUI() {
        mEtName = (TextView) findViewById(R.id.name_et);
        mEtPwd = (TextView) findViewById(R.id.pwd_et);
        mBtnLogin = (Button) findViewById(R.id.login_btn);
        mBtnLogin.setOnClickListener(this);

        mRegisterBtn = (Button) findViewById(R.id.register_btn);
        mRegisterBtn.setOnClickListener(this);

        mBtnReset = (Button) findViewById(R.id.reset_btn);
        mBtnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnLogin)) {
            doLogin();
        } else if (v.equals(mRegisterBtn)) {
            doRegister();
        } else if (v.equals(mBtnReset)) {
            doReset();
        }
    }

    /**
     * 重置密码
     */
    private void doReset() {
        ActivityUtils.startActivity(this, ResetActivity.class, false);
    }

    /**
     * 注册
     */
    private void doRegister() {
        ActivityUtils.startActivity(this, RegisterActivity.class, false);
    }

    /**
     * 登录
     */
    private void doLogin() {
        String username = mEtName.getText().toString();
        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
            tip("用户名或密码不能为空");
            return;
        }

        // 发送网络请求
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_LOGIN, username, pwd);
    }

    @Override
    public void initController() {
        mController = new UserController(this);
        mController.setIContrllerListenner(this);
    }


    /**
     * 动作直接结束
     *
     * @param action 动作
     * @param values 结果
     */
    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    /**
     * 登录结果
     *
     * @param msg 消息
     */
    @Override
    protected void handleMessage(Message msg) {
        if (msg.what == IdiyMessage.MSG_ACTION_LOGIN_RESULT) {
            RResult rResult = (RResult) msg.obj;
            tip(rResult.success ? "登录成功" : "登录失败 : " + rResult.errorMsg);
            if (rResult.success) {
                // 1. 将用户信息保存到Application中
                RLoginResult rLoginResult = JSON.parseObject(rResult.result, RLoginResult.class);
                JDApplication application = (JDApplication) getApplication();
                application.mRLoginResult = rLoginResult;
                // 2. 保存用户信息到数据库.
                String username = mEtName.getText().toString();
                String pwd = mEtPwd.getText().toString();
                mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_SAVE_DB, username, pwd);
            }
        } else if (msg.what == IdiyMessage.MSG_ACTION_SAVE_DB_RESULT) {

            if ((boolean) msg.obj) {
                ActivityUtils.startActivity(this, MainActivity.class, true);
            } else {
                tip("登录失败 : " + "数据异常");
            }
        } else if (msg.what == IdiyMessage.MSG_ACTION_GET_DB_RESULT) {
            if (msg.obj != null) {
                UserDao.UserInfo userInfo = (UserDao.UserInfo) msg.obj;
                mEtName.setText(userInfo.username);
                mEtPwd.setText(userInfo.pwd);
            }
        }

    }
}
