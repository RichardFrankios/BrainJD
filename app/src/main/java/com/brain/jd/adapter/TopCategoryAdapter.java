package com.brain.jd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brain.jd.R;
import com.brain.jd.domain.RTopCategoryBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @author : Brian
 * @date : 2017/7/19
 */

public class TopCategoryAdapter extends JDBaseAdapter<RTopCategoryBean> {




    public TopCategoryAdapter(Context ctx) {
        super(ctx);
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.top_category_item, null);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RTopCategoryBean rTopCategoryBean = mData.get(position);

        holder.tv_category.setText(rTopCategoryBean.getName());

        holder.divider.setVisibility((position != mCurrentSelect) ? View.VISIBLE : View.INVISIBLE);
        holder.tv_category.setSelected((position == mCurrentSelect));
        if (position == mCurrentSelect) {
            holder.tv_category.setBackgroundResource(R.drawable.tongcheng_all_bg01);

        } else {
            holder.tv_category.setBackgroundColor(0xFFFAFAFA);
        }

        return convertView;
    }


    static class ViewHolder{
        @ViewInject(R.id.divider)
        View divider;

        @ViewInject(R.id.tv_category)
        TextView tv_category;
    }
}
