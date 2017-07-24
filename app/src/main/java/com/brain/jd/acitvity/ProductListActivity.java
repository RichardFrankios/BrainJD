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

import com.brain.jd.R;
import com.brain.jd.adapter.BrandAdapter;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.CategoryController;
import com.brain.jd.domain.RBrand;
import com.brain.jd.ui.SubCategoryView;
import com.brain.jd.utils.FixedViewUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class ProductListActivity extends JDBaseActivity {


    private static final String TAG = "ProductListActivity";

    private double mCategoryId;


    @ViewInject(R.id.gv_brand)
    private GridView mGvBrand;

    @ViewInject(R.id.drawerlayout)
    private DrawerLayout mDrawerlayout;




    private BrandAdapter mBrandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        initIntentData();

        initUI();

        initController();

        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_BRAND_CATEGORY, mCategoryId);

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


    }

    private void initIntentData() {
        Intent intent = getIntent();
        mCategoryId = intent.getDoubleExtra(SubCategoryView.INTENT_EXTRA_DATA_CATEGORY_ID, -1);
        if (mCategoryId == -1) {
            tip("数据异常！！！");
            finish();
        }
        Log.d(TAG, "initIntentData: " + mCategoryId);
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
        }
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

    }

    @Event(R.id.iv_go_back)
    private void onIvGoBackClicked(View view) {
        finish();
    }


}
