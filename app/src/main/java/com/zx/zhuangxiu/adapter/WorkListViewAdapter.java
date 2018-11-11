package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.FoundWorkDetailsActivity;
import com.zx.zhuangxiu.activity.MapActivity;
import com.zx.zhuangxiu.model.ShouYeZgzOne;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

public class WorkListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShouYeZgzOne.DataBean> mList;

    private int pkId;

    public WorkListViewAdapter(Context mContext, List<ShouYeZgzOne.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.work_fragment_item, null);
            holder.work_item_title = (TextView) convertView.findViewById(R.id.work_item_title);
            holder.work_item_xiaotitle = (TextView) convertView.findViewById(R.id.work_item_xiaotitle);
            holder.work_item_content = (TextView) convertView.findViewById(R.id.work_item_content);
            holder.work_item_address = (TextView) convertView.findViewById(R.id.work_item_address);
            holder.work_item_img = (RoundImageView) convertView.findViewById(R.id.work_item_img);
            holder.work_item_rl = (RelativeLayout)convertView.findViewById(R.id.work_item_rl);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.work_item_title.setText(""+mList.get(position).getName());
            holder.work_item_xiaotitle.setText(""+mList.get(position).getEducation());
            holder.work_item_address.setText("详情描述: "+mList.get(position).getExperience());
            holder.work_item_address.setText("地址: "+mList.get(position).getAddress());

//            Log.d("xxx", "首页找工作---------------"+collectList.getJskSysAnnex());

            String imgUrl = mList.get(position).getImgUrl();
            Picasso.with(mContext).load(URLS.HTTP+imgUrl).error(R.mipmap.logo_zhanwei).fit().into(holder.work_item_img);
        }

        holder.work_item_address.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(mContext, MapActivity.class);
        intent.putExtra("map",mList.get(position).getAddress());
        mContext.startActivity(intent);
    }
});

        holder.work_item_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoundWorkDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }

    public class ViewHolder {

        RoundImageView work_item_img;
        TextView work_item_title, work_item_xiaotitle, work_item_content, work_item_address;
        RelativeLayout work_item_rl;

    }
}
