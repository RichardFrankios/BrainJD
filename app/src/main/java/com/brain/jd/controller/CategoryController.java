package com.brain.jd.controller;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.domain.RBrand;
import com.brain.jd.domain.RProductListBean;
import com.brain.jd.domain.RResult;
import com.brain.jd.domain.RSecondSubBean;
import com.brain.jd.domain.RTopCategoryBean;
import com.brain.jd.domain.SProductListParam;
import com.brain.jd.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class CategoryController extends JDBaseController {

    private static final String TAG = "CategoryController";



    public CategoryController(Context context) {
        super(context);
    }

    @Override
    public void handleAsyncMessage(int pAction, Object[] pValues) {
        switch (pAction) {
            case IdiyMessage.MSG_ACTION_TOP_CATEGORY:
                loadTopCategory();
                break;
            case IdiyMessage.MSG_ACTION_SECOND_CATEGORY:
                loadSecondCategory((double) pValues[0]);
                break;

            case IdiyMessage.MSG_ACTION_BRAND_CATEGORY:
                loadBrandCategory((Double) pValues[0]);
                break;

            case IdiyMessage.MSG_ACTION_SEARCH_PRODUCT:
                loadProductList((SProductListParam) pValues[0]);
                break;





        }
    }

    private void loadProductList(SProductListParam param) {

        String productStr = NetWorkUtil.doPost(INetWorkConst.SEARCH_PRODUCT_URL, param.getHashMapValue());
        Log.d(TAG, "loadProductList: " + productStr);

        RResult rResult = JSON.parseObject(productStr, RResult.class);
        RProductListBean rProductListBean = null;
        if (rResult.isSuccess()) {
            rProductListBean = JSON.parseObject(rResult.getResult(), RProductListBean.class);

        }
        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_SEARCH_PRODUCT_RESULT, rProductListBean );
        }
    }

    /**
     * loadBrandCategory
     */
    private void loadBrandCategory(double id) {
        String json = NetWorkUtil.doGet(INetWorkConst.BRAND_CATEGORY_URL + "?categoryId=" + (int)id);
        RResult rResult = JSON.parseObject(json, RResult.class);
        List<RBrand> rBrands = new ArrayList<>();

        if (rResult.isSuccess()) {
            rBrands = JSON.parseArray(rResult.getResult(), RBrand.class);
        }

        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_BRAND_CATEGORY_RESULT, rBrands);
        }
    }

    /**
     * loadSecondCategory
     */
    private void loadSecondCategory(double id) {
        String json = NetWorkUtil.doGet(INetWorkConst.TOP_CATEGORY_URL + "?parentId=" + (int)id);
        RResult rResult = JSON.parseObject(json, RResult.class);
        List<RSecondSubBean> rSecondSubBeen = new ArrayList<>();

        if (rResult.isSuccess()) {
            rSecondSubBeen = JSON.parseArray(rResult.getResult(), RSecondSubBean.class);
        }

        if (mIContrllerListenner != null) {
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_SECOND_CATEGORY_RESULT, rSecondSubBeen);
        }
    }

    /**
     * load top category
     */
    private void loadTopCategory() {
        String json = NetWorkUtil.doGet(INetWorkConst.TOP_CATEGORY_URL);
        RResult rResult = JSON.parseObject(json, RResult.class);
        List<RTopCategoryBean> rTopCategoryBeen = new ArrayList<>();

        // Log.d(TAG, "loadTopCategory: " + rResult.getResult());
        if (rResult.isSuccess()) {
            rTopCategoryBeen = JSON.parseArray(rResult.getResult(), RTopCategoryBean.class);
        }

        if (mIContrllerListenner != null) {
            Log.d(TAG, "loadTopCategory: " + rTopCategoryBeen);
            mIContrllerListenner.onMessageResult(IdiyMessage.MSG_ACTION_TOP_CATEGORY_RESULT, rTopCategoryBeen);
        }
    }
}
