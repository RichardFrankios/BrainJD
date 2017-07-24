package com.brain.jd.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.brain.jd.R;
import com.brain.jd.acitvity.ProductListActivity;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.CategoryController;
import com.brain.jd.domain.RSecondSubBean;
import com.brain.jd.domain.RThirdSubBean;
import com.brain.jd.domain.RTopCategoryBean;
import com.brain.jd.listenner.IContrllerListenner;
import com.brain.jd.listenner.IViewContainer;
import com.brain.jd.view.AutoBreakViewGroup;
import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 子分类
 * @author : Brian
 * @date : 2017/7/19
 */

public class SubCategoryView extends FlexScrollView implements IViewContainer, IContrllerListenner{

    private static final String TAG = "SubCategoryView";

    /**
     * intent extra data flag
     */
    public static final String INTENT_EXTRA_DATA_TOP_CATEGORY_ID = "top_category_id";
    public static final String INTENT_EXTRA_DATA_THIRD_CATEGORY_ID = "third_category_id";

    @ViewInject(R.id.child_container_ll)
    private LinearLayout mLlChildContainer;

    private CategoryController mController;

    private List<List<RThirdSubBean>> mThirdCategory = new ArrayList<>();

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.MSG_ACTION_SECOND_CATEGORY_RESULT:
                    handleSecondCategory((List<RSecondSubBean>) msg.obj);
                    break;
            }
        }
    };


    private void handleSecondCategory(List<RSecondSubBean> secondSubBeans) {
        int size = secondSubBeans.size();
        mThirdCategory = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            RSecondSubBean rSecondSubBean = secondSubBeans.get(i);
            // add second
            View scView = View.inflate(getContext(), R.layout.view_item_category, null);
//            View scView = View.inflate(getContext(), R.layout.view_item_icon_name, null);
            TextView secondTitle = (TextView) scView.findViewById(R.id.tv_second_title);
            secondTitle.setText(rSecondSubBean.getName());

            mLlChildContainer.addView(scView);
            scView.requestLayout();


            AutoBreakViewGroup abvg = (AutoBreakViewGroup) scView.findViewById(R.id.abvg_category);

            // 添加点击事件
            abvg.setOnItemClickListener(new AutoBreakViewGroup.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, int parentId) {
                    Log.d(TAG, "onItemClick: " + mThirdCategory.get(parentId).get(position).getName());


                    Intent intent = new Intent(getContext(), ProductListActivity.class);
                    intent.putExtra(INTENT_EXTRA_DATA_TOP_CATEGORY_ID, mRTopCategoryBean.getId());
                    intent.putExtra(INTENT_EXTRA_DATA_THIRD_CATEGORY_ID, mThirdCategory.get(parentId).get(position).getId());
                    getContext().startActivity(intent);

                }
            });
            
//            // 三级数据
            List<RThirdSubBean> rThirdSubBeen = JSON.parseArray(rSecondSubBean.getThirdCategory(), RThirdSubBean.class);
            mThirdCategory.add(rThirdSubBeen);

            for (int j = 0; j < rThirdSubBeen.size(); j++) {
                Log.d(TAG, "handleSecondCategory: " + j);

                RThirdSubBean rThirdSubBean = rThirdSubBeen.get(j);
                View viewItem = View.inflate(getContext(), R.layout.view_item_icon_name, null);
                TextView tvName = (TextView) viewItem.findViewById(R.id.tv_name);
                ImageView ivIcon = (ImageView) viewItem.findViewById(R.id.iv_icon);

                tvName.setText(rThirdSubBean.getName());
                Glide.with(getContext())
                        .load(INetWorkConst.BASE_URL + rThirdSubBean.getBannerUrl())
                        .into(ivIcon);

                abvg.setCurrentId(i);

                abvg.addView(viewItem);
            }
        }
    }


    private RTopCategoryBean mRTopCategoryBean;

    public SubCategoryView(Context context) {
        this(context, null);
    }

    public SubCategoryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubCategoryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initController();

    }

    private void initController() {
        mController = new CategoryController(getContext());
        mController.setIContrllerListenner(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 布局加载完成后调用
        x.view().inject(this);
    }

    @Override
    public void onShow(Object... values) {
        mRTopCategoryBean = (RTopCategoryBean) values[0];
        // 移除所有的额VIew
        mLlChildContainer.removeAllViews();
        // init Top Banner
        initBannerView();

        // 加载二级数据
        loadDataFromServer();


        Log.d(TAG, "onShow: " + mRTopCategoryBean);
    }

    private void loadDataFromServer() {
        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_SECOND_CATEGORY, mRTopCategoryBean.getId());
    }

    /**
     * 有些没有BannerView
     */
    private void initBannerView() {
        if (mRTopCategoryBean.getBannerUrl() == null) {
            return;
        }
        ImageView imageView = new ImageView(getContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(8, 4, 8, 4);

        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(getContext())
                .load(INetWorkConst.BASE_URL + mRTopCategoryBean.getBannerUrl())
                .into(imageView);

        mLlChildContainer.addView(imageView);
    }

    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }
}


































