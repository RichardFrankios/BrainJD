package com.brain.jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.jd.R;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.domain.RRecommendBean;
import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class RecommendAdapter extends JDBaseAdapter<RRecommendBean> {


    public RecommendAdapter(Context ctx) {
        super(ctx);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.recommend_gv_item, null);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RRecommendBean rRecommendBean = mData.get(position);
        Glide.with(mContext)
                .load(INetWorkConst.BASE_URL + rRecommendBean.getIconUrl())
                .into(holder.image_iv);

        holder.name_tv.setText(rRecommendBean.getName());
        holder.price_tv.setText("$" + rRecommendBean.getPrice() + "");

        return convertView;
    }


    static class ViewHolder{
        @ViewInject(R.id.image_iv)
        ImageView image_iv;

        @ViewInject(R.id.name_tv)
        TextView name_tv;

        @ViewInject(R.id.price_tv)
        TextView price_tv;
    }
}
