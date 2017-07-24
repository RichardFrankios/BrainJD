package com.brain.jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brain.jd.R;
import com.brain.jd.domain.RBrand;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class BrandAdapter extends JDBaseAdapter<RBrand> {


    public BrandAdapter(Context ctx) {
        super(ctx);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Button brandBtn;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.brand_lv_item_layout, null);
            brandBtn = (Button) convertView.findViewById(R.id.brand_btn);

            convertView.setTag(brandBtn);
        } else {
            brandBtn = (Button) convertView.getTag();
        }
        RBrand rBrand = mData.get(position);

        brandBtn.setText(rBrand.getName());
        brandBtn.setSelected(mCurrentSelect == position);
        return convertView;
    }

}
