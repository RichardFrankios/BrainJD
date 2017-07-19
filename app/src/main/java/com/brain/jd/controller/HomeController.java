package com.brain.jd.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.domain.BannerBean;
import com.brain.jd.domain.RResult;
import com.brain.jd.domain.RSecondKillBean;
import com.brain.jd.utils.NetWorkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页控制器
 * @author : Brian
 * @date : 2017/6/23
 */

public class HomeController extends JDBaseController {
    private static final String TAG = "HomeController";

    public HomeController(Context context) {
        super(context);
    }


    @Override
    public void handleAsyncMessage(int pAction, Object[] pValues) {
        switch (pAction) {
            case IdiyMessage.MSG_ACTION_BANNER:
                loadBanner((Integer)pValues[0]);
                break;

            case IdiyMessage.MSG_ACTION_SECOND_KILL:
                loadSecondKill();
                break;

        }
    }

    /**
     * 加载秒杀数据
     */
    private void loadSecondKill() {
        String json = NetWorkUtil.doGet(INetWorkConst.SECOND_KILL_URL);
        RResult rResult = JSON.parseObject(json, RResult.class);
        List<RSecondKillBean> rSecondKillBeen = new ArrayList<>();

        if (rResult.isSuccess()) {
            try {
                JSONObject jsonObject = new JSONObject(rResult.result);
                String rows = jsonObject.optString("rows");
                rSecondKillBeen = JSON.parseArray(rows, RSecondKillBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_SECOND_KILL_RESULT, rSecondKillBeen);
        }
    }

    /**
     * 加载Banner
     */
    private void loadBanner(Integer type) {
        String json = NetWorkUtil.doGet(INetWorkConst.BANNER_URL + "?adKind=" + type);
        RResult rResult = JSON.parseObject(json, RResult.class);
        if (rResult.success) {
            List<BannerBean> bannerBeen = JSON.parseArray(rResult.result, BannerBean.class);
            if (mIContrllerListenner != null && bannerBeen != null) {
                mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_BANNER_RESULT, bannerBeen);
            }
        }

    }
}
