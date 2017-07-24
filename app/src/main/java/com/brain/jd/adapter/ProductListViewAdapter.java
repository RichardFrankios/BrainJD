package com.brain.jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.jd.R;
import com.brain.jd.domain.RProductListBean;
import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class ProductListViewAdapter extends JDBaseAdapter<RProductListBean.RProductInfoBean> {


    public ProductListViewAdapter(Context ctx) {
        super(ctx);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.product_lv_item, null);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RProductListBean.RProductInfoBean rProductInfoBean = mData.get(position);

        Glide.with(mContext)
                .load(rProductInfoBean.getIconUrl())
                .into(holder.product_iv);

        holder.name_tv.setText(rProductInfoBean.getName());
        holder.price_tv.setText("" + (int) rProductInfoBean.getPrice() + "");
        holder.commrate_tv.setText(rProductInfoBean.getCommentCount());
        return convertView;
    }


    static class ViewHolder{
        @ViewInject(R.id.product_iv)
        ImageView product_iv;


        @ViewInject(R.id.name_tv)
        TextView name_tv;

        @ViewInject(R.id.commrate_tv)
        TextView commrate_tv;

        @ViewInject(R.id.price_tv)
        TextView price_tv;

        @ViewInject(R.id.shop_car_iv)
        ImageView shop_car_iv;
    }
}
