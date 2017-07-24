package com.brain.jd.acitvity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.brain.jd.R;
import com.brain.jd.adapter.BrandAdapter;
import com.brain.jd.adapter.ProductListViewAdapter;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.CategoryController;
import com.brain.jd.domain.RBrand;
import com.brain.jd.domain.RProductListBean;
import com.brain.jd.domain.SProductListParam;
import com.brain.jd.ui.SubCategoryView;
import com.brain.jd.ui.pop_window.ProductSortPopWindow;
import com.brain.jd.utils.FixedViewUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class ProductListActivity extends JDBaseActivity {


    private static final String TAG = "ProductListActivity";

    private double mTopCategoryId;
    private double mThirdCategoryId;


    @ViewInject(R.id.gv_brand)
    private GridView mGvBrand;

    @ViewInject(R.id.drawerlayout)
    private DrawerLayout mDrawerlayout;

    @ViewInject(R.id.all_indicator)
    private TextView mTvAllIndicator;

    @ViewInject(R.id.product_lv)
    private ListView mLvProduct;




    private BrandAdapter mBrandAdapter;
    private ProductSortPopWindow mProductSortPopWindow;
    private SProductListParam mSProductListParam;
    private ProductListViewAdapter mProductListViewAdapter;
    private List<RProductListBean.RProductInfoBean> mRProductInfoBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        initIntentData();

        initUI();

        initController();

        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_BRAND_CATEGORY, mTopCategoryId);

        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_SEARCH_PRODUCT, mSProductListParam);

    }

    @Override
    public void initController() {
        mController = new CategoryController(this);
        mController.setIContrllerListenner(this);
    }

    @Override
    public void initUI() {
        x.view().inject(this);

        mBrandAdapter = new BrandAdapter(this);
        mGvBrand.setAdapter(mBrandAdapter);
        mGvBrand.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mBrandAdapter.setCurrentSelect(position);
                mBrandAdapter.notifyDataSetChanged();
            }
        });


        mProductListViewAdapter = new ProductListViewAdapter(this);
        mLvProduct.setAdapter(mProductListViewAdapter);
        mLvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    private void initIntentData() {
        Intent intent = getIntent();
        mTopCategoryId = intent.getDoubleExtra(SubCategoryView.INTENT_EXTRA_DATA_TOP_CATEGORY_ID, -1);
        mThirdCategoryId = intent.getDoubleExtra(SubCategoryView.INTENT_EXTRA_DATA_THIRD_CATEGORY_ID, -1);
        if (mTopCategoryId == -1 || (mThirdCategoryId == -1)) {
            tip("数据异常！！！");
            finish();
        }
        Log.d(TAG, "initIntentData: " + mTopCategoryId);

        mSProductListParam = new SProductListParam(mThirdCategoryId);

    }

    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.MSG_ACTION_BRAND_CATEGORY_RESULT:
                handleBrandCategory((List<RBrand>)msg.obj);
                break;

            case IdiyMessage.MSG_ACTION_SEARCH_PRODUCT_RESULT:
                handleProductList((RProductListBean)msg.obj);
                break;


        }
    }

    /**
     * deal product list
     */
    private void handleProductList(RProductListBean products) {
        if (products == null) {
            tip("网络异常");
            return;
        }
        mRProductInfoBeen = JSON.parseArray(products.getRows(), RProductListBean.RProductInfoBean.class);
        if (mRProductInfoBeen == null) {
            tip("数据异常");
            return;
        }
        mProductListViewAdapter.setDatas(mRProductInfoBeen);
        mProductListViewAdapter.notifyDataSetChanged();
    }

    /**
     * 处理数据
     */
    private void handleBrandCategory(List<RBrand> obj) {

        mBrandAdapter.setDatas(obj);
        mBrandAdapter.notifyDataSetChanged();
        // 重新设置高度
        FixedViewUtil.setGridViewHeightBasedOnChildren(mGvBrand, 3);
    }


    @Event(R.id.btn_confirm)
    private void onBtnConfirmClicked(View view) {
        mDrawerlayout.closeDrawer(Gravity.RIGHT);
    }
    @Event(R.id.btn_reset)
    private void onBtnResetClicked(View view) {
        mSProductListParam = new SProductListParam(mThirdCategoryId);
    }

    @Event(R.id.iv_go_back)
    private void onGoBackClicked(View view) {
        finish();
    }

    @Event(R.id.all_indicator)
    private void onAllIndicatorClicked(View view) {
        if (mProductSortPopWindow == null) {
            mProductSortPopWindow = new ProductSortPopWindow(this);
            mProductSortPopWindow.setProductSortChangeListener(new ProductSortPopWindow.ProductSortChangeListener() {
                @Override
                public void onSortChanged(int action) {
                    mSProductListParam.setFilterType(action);
                    refreshList();
                }
            });
        }
        mProductSortPopWindow.onShow(mTvAllIndicator);
    }

    /**
     * 刷新数据
     */
    private void refreshList() {
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_SEARCH_PRODUCT, mSProductListParam);
    }

    @Event({R.id.jd_take_tv, R.id.paywhenreceive_tv, R.id.justhasstock_tv})
    private void onDeliveryTypeClicked(View view) {
        view.setSelected(!view.isSelected());
    }




}
