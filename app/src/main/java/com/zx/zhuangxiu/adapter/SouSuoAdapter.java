package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.DetailsActivity;
import com.zx.zhuangxiu.model.SearchShopBean;
import com.zx.zhuangxiu.model.SyFuWuss;
import com.zx.zhuangxiu.model.SySearchTwo;
import com.zx.zhuangxiu.model.WorkerListItem;

import java.util.List;

public class SouSuoAdapter extends BaseAdapter{


    private Context mContext;
    private List<SearchShopBean.DataBean> mList;

    public SouSuoAdapter(Context mContext, List<SearchShopBean.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sy_sousuo_item, null);
            holder.sousuo_title = (TextView) convertView.findViewById(R.id.sousuo_title);
            holder.sousuo_xiaotitle = (TextView) convertView.findViewById(R.id.sousuo_xiaotitle);
            holder.sousuo_price = (TextView) convertView.findViewById(R.id.sousuo_price);
            holder.sousuo_img = (ImageView) convertView.findViewById(R.id.sousuo_img);
            holder.rl = (RelativeLayout)convertView.findViewById(R.id.rl);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        SearchShopBean.DataBean collectList = mList.get(position);
            holder.sousuo_title.setText(""+collectList.getCdname());
            holder.sousuo_xiaotitle.setText(""+collectList.getSimple());
            holder.sousuo_price.setText("￥"+collectList.getPrice());
            Picasso.with(mContext).load(URLS.HTTP+collectList.getPicture()).error(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(holder.sousuo_img);


        //跳转产品的详情
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder {

        ImageView sousuo_img;
        TextView sousuo_title, sousuo_xiaotitle, sousuo_price;
        RelativeLayout rl;

    }

}
