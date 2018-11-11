package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.DetailsActivity;
import com.zx.zhuangxiu.model.SpTuiJianTwo;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;


public class HorizontalListViewAdapter extends BaseAdapter{


    private Context mContext;
    private List<SpTuiJianTwo.DataBean> mList;

    public HorizontalListViewAdapter(Context mContext, List<SpTuiJianTwo.DataBean> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_listview_item, null);
            holder.horizon_listview_img = (RoundImageView) convertView.findViewById(R.id.horizon_listview_img);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load(URLS.HTTP+mList.get(position).getPicture()).error(R.mipmap.logo_zhanwei).fit().into(holder.horizon_listview_img);

        holder.horizon_listview_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                bundle.putInt("shangpin",0);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }


    public class ViewHolder {

        RoundImageView horizon_listview_img;


    }
}
