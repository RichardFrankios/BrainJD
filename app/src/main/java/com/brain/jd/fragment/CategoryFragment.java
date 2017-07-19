package com.brain.jd.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.brain.jd.R;
import com.brain.jd.adapter.TopCategoryAdapter;
import com.brain.jd.consts.IdiyMessage;
import com.brain.jd.controller.CategoryController;
import com.brain.jd.domain.RTopCategoryBean;
import com.brain.jd.ui.SubCategoryView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 主页Fragment
 * @author : Brian
 * @date : 2017/6/23
 */

public class CategoryFragment extends JDBaseFragment{

    private static final String TAG = "CategoryFragment";

    @ViewInject(R.id.top_lv)
    private ListView mLvTopCategory;

    @ViewInject(R.id.subcategory)
    private SubCategoryView mSubcategoryView;


    private CategoryController mController;
    private TopCategoryAdapter mTopCategoryAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_category, null);

        x.view().inject(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        initUI();

        initController();

        initData();

    }

    private void initData() {
        // 1. TopCategory

        mController.sendAsyncMessage(IdiyMessage.MSG_ACTION_TOP_CATEGORY, 0);

    }

    @Override
    public void initController() {
        mController = new CategoryController(getActivity());
        mController.setIContrllerListenner(this);
    }



    @Override
    public void initUI() {
        // top category
        mTopCategoryAdapter = new TopCategoryAdapter(getActivity());
        mLvTopCategory.setAdapter(mTopCategoryAdapter);
        mLvTopCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTopCategoryAdapter.setCurrentSelect(position);
                mTopCategoryAdapter.notifyDataSetChanged();

                RTopCategoryBean rTopCategoryBean = (RTopCategoryBean) mTopCategoryAdapter.getItem(position);

                // 显示数据
                mSubcategoryView.onShow(rTopCategoryBean);

            }
        });
    }

    @Override
    public void onMessageResult(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
    }

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case IdiyMessage.MSG_ACTION_TOP_CATEGORY_RESULT:
                handleTopCategoryResult(msg.obj);
                break;
        }
    }

    private void handleTopCategoryResult(Object obj) {
        Log.d(TAG, "handleTopCategoryResult: ");
        // top category
        mTopCategoryAdapter.setDatas((List<RTopCategoryBean>) obj);
        mTopCategoryAdapter.notifyDataSetChanged();
    }
}
