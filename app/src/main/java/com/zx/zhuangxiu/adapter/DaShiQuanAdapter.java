package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.ToutiaoBena;

import java.util.List;

public class DaShiQuanAdapter extends BaseAdapter{

    private Context mContext;
    private List<ToutiaoBena.DataBean> mList;

    public DaShiQuanAdapter(Context mContext, List<ToutiaoBena.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.dashiquan_item, null);
            holder.toutiao_item_title = (TextView) convertView.findViewById(R.id.toutiao_item_title);
            holder.toutiao_item_lx = (TextView) convertView.findViewById(R.id.toutiao_item_lx);
            holder.toutiao_item_details = (ImageView) convertView.findViewById(R.id.toutiao_item_details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.toutiao_item_title.setText(""+mList.get(position).getTitle());
            holder.toutiao_item_lx.setText(""+mList.get(position).getInfo());
            Picasso.with(mContext).load(URLS.HTTP+mList.get(position).getImg()).error(R.mipmap.logo_zhanwei).fit().into(holder.toutiao_item_details);
        }

        return convertView;
    }

    public class ViewHolder {

        TextView toutiao_item_title, toutiao_item_lx;
        ImageView toutiao_item_details;
    }

}
