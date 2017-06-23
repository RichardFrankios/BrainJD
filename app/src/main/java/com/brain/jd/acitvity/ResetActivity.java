package com.brain.jd.acitvity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.brain.jd.R;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.UserController;
import com.brain.jd.domain.RResult;

/**
 * @author : Brian
 * @date : 2017/6/22
 */

public class ResetActivity extends JDBaseActivity {


    private EditText mEtUsername;
    private Button mBtnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        initUI();
        initController();
    }

    @Override
    public void initUI() {
        mEtUsername = (EditText) findViewById(R.id.username_et);
        mBtnNext = (Button) findViewById(R.id.next_btn);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doReset();
            }
        });
    }

    /**
     * 进行重置密码操作
     */
    private void doReset() {
        String username = mEtUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            tip("用户名不能为空");
            return;
        }

        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_RESET, username);

    }

    @Override
    public void initController() {
        mController = new UserController(this);
        mController.setIContrllerListenner(this);
    }

    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    protected void handleMessage(Message msg) {
        RResult rResult = (RResult) msg.obj;
        tip(rResult.success ? "密码重置为 : 123456" : "密码重置失败 : " + rResult.errorMsg);
        if (rResult.success) {
            finish();
        }
    }
}
