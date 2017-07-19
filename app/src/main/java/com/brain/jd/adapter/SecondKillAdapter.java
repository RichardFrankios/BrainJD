package com.brain.jd.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.jd.R;
import com.brain.jd.consts.INetWorkConst;
import com.brain.jd.domain.RSecondKillBean;
import com.bumptech.glide.Glide;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class SecondKillAdapter extends JDBaseAdapter<RSecondKillBean> {


    public SecondKillAdapter(Context ctx) {
        super(ctx);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.home_seckill_item, null);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RSecondKillBean rSecondKillBean = mData.get(position);
        Glide.with(mContext)
                .load(INetWorkConst.BASE_URL + rSecondKillBean.getIconUrl())
                .into(holder.image_iv);

        holder.nowprice_tv.setText("$" + rSecondKillBean.getPointPrice() + "");
        holder.normalprice_tv.setText(" $" + rSecondKillBean.getAllPrice() + " ");
        holder.normalprice_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        return convertView;
    }


    static class ViewHolder{
        @ViewInject(R.id.image_iv)
        ImageView image_iv;

        @ViewInject(R.id.nowprice_tv)
        TextView nowprice_tv;

        @ViewInject(R.id.normalprice_tv)
        TextView normalprice_tv;
    }
}
