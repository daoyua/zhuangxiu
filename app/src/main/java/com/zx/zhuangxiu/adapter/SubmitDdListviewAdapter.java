package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.SubmitDdThree;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

public class SubmitDdListviewAdapter extends BaseAdapter{

    private Context mContext;
    private List<SubmitDdThree> mList;

    public SubmitDdListviewAdapter(Context mContext, List<SubmitDdThree> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.submitdingdan_item, null);
            holder.dingdan_item_title = (TextView) convertView.findViewById(R.id.dingdan_item_title);
            holder.dingdan_item_price = (TextView) convertView.findViewById(R.id.dingdan_item_price);
            holder.dingdan_item_num = (TextView) convertView.findViewById(R.id.dingdan_item_num);
            holder.dingdan_item_img = (RoundImageView)convertView.findViewById(R.id.dingdan_item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            SubmitDdThree collectList = mList.get(position);
            holder.dingdan_item_title.setText(""+collectList.getRecordName());
            holder.dingdan_item_price.setText("￥"+collectList.getRecordPrice());
            holder.dingdan_item_num.setText("X "+collectList.getPayNum());

            Picasso.with(mContext).load(collectList.getRecordPhoto()).error(R.mipmap.logo_zhanwei).fit().into(holder.dingdan_item_img);
        }

        return convertView;
    }

    public class ViewHolder {

        TextView dingdan_item_title, dingdan_item_price, dingdan_item_num;
        RoundImageView dingdan_item_img;

    }

}
