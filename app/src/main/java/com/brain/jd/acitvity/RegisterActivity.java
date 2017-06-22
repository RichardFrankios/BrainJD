package com.brain.jd.acitvity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.brain.jd.R;
import com.brain.jd.constants.IdiyMessage;
import com.brain.jd.controller.UserController;
import com.brain.jd.domain.RResult;

/**
 * @author : Brian
 * @date : 2017/6/22
 */

public class RegisterActivity extends BaseActivity {


    private static final String TAG = "RegisterActivity";

    private TextView mEtUsername;
    private TextView mEtPwd;
    private TextView mEtSurePwd;
    private CheckBox mCbAgree;
    private Button mBtnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initUI();
        initController();
    }

    @Override
    public void initUI() {
        mEtUsername = (TextView) findViewById(R.id.username_et);
        mEtPwd = (TextView) findViewById(R.id.pwd_et);
        mEtSurePwd = (TextView) findViewById(R.id.surepwd_et);
        mCbAgree = (CheckBox) findViewById(R.id.agree_cb);
        mBtnNext = (Button) findViewById(R.id.next_btn);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 注册按钮被电击");
                doRegistUser();
            }
        });
    }

    /**
     * 用户注册
     */
    private void doRegistUser() {
        String username = mEtUsername.getText().toString();
        String pwd = mEtPwd.getText().toString();
        String surePwd = mEtSurePwd.getText().toString();
        if (!isInputLagel(username, pwd, surePwd)) {
            return;
        }
        // 发送注册信息到服务器
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_REGISTER, username, pwd);
    }

    /**
     * 输入是否合法
     *
     * @param username
     * @param pwd
     * @param surePwd
     * @return
     */
    private boolean isInputLagel(String username, String pwd, String surePwd) {

        if (TextUtils.isEmpty(username)) {
            tip("用户名不能为空");
        } else if (TextUtils.isEmpty(pwd)) {
            tip("密码不能为空");
        } else if (!pwd.equals(surePwd)) {
            tip("确认密码不正确");
        } else if (!mCbAgree.isChecked()) {
            tip("请同意京东用户协议");
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void initController() {
        mController = new UserController();
        mController.setIContrllerListenner(this);
    }

    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    protected void handleMessage(Message msg) {
        RResult rResult = (RResult) msg.obj;
        tip(rResult.success ? "注册成功" : "注册失败 : " + rResult.errorMsg);
        if (rResult.success) {
            finish();
        }
    }
}
