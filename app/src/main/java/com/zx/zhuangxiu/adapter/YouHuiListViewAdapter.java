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
import com.zx.zhuangxiu.model.YouhuihdBean;

import java.util.List;

public class YouHuiListViewAdapter extends BaseAdapter{

    private Context mContext;
    private List<YouhuihdBean.DataBean> mList;

    public YouHuiListViewAdapter(Context mContext, List<YouhuihdBean.DataBean> mList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.youhui_listview_item, null);
            holder.youhuishijian = (TextView) convertView.findViewById(R.id.youhui_shijian);
            holder.youhuibiaoti = (TextView) convertView.findViewById(R.id.youhui_title);
            holder.youhuineirong = (TextView) convertView.findViewById(R.id.youhui_neirong);
            holder.youhuiimage = (ImageView) convertView.findViewById(R.id.youhui_image);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.youhuishijian.setText(""+mList.get(position).getTime());
            holder.youhuibiaoti.setText(""+mList.get(position).getWn_title());
            holder.youhuineirong.setText(""+mList.get(position).getWn_content());
            Picasso.with(mContext).load(URLS.HTTP+mList.get(position).getWn_image()).error(R.mipmap.logo_zhanwei).fit().into(holder.youhuiimage);


        }

        return convertView;
    }

    public class ViewHolder {

        TextView youhuishijian, youhuibiaoti, youhuineirong;
        ImageView youhuiimage;
    }
}
