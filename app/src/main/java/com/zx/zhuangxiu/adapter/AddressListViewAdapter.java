package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.AddressTwo;
import com.zx.zhuangxiu.model.JinEMxTwo;
import com.zx.zhuangxiu.utils.ToTime;

import java.util.List;

public class AddressListViewAdapter extends BaseAdapter{

    private Context mContext;
    private List<AddressTwo> mList;

    public AddressListViewAdapter(Context mContext, List<AddressTwo> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.address_item, null);
            holder.address_item_name = (TextView) convertView.findViewById(R.id.address_item_name);
            holder.address_item_mobile = (TextView) convertView.findViewById(R.id.address_item_mobile);
            holder.address_item_address = (TextView) convertView.findViewById(R.id.address_item_address);
            holder.address_item_isMoren = (TextView) convertView.findViewById(R.id.address_item_isMoren);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (mList.size() != 0) {
            AddressTwo collectList = mList.get(position);
            holder.address_item_name.setText("收货人: "+collectList.getUserName()+"你好嗷嗷红花红");
            holder.address_item_address.setText("详细地址: "+collectList.getRegion()+collectList.getAddress()+"adshjdgahfadshhad");
            holder.address_item_mobile.setText("联系方式: "+collectList.getMobile()+"15100341203");

            if(collectList.getIsDefault().equals("1")){
                holder.address_item_isMoren.setVisibility(View.VISIBLE);
            }else {
                holder.address_item_isMoren.setVisibility(View.GONE);
            }
        }

        return convertView;
    }


    public class ViewHolder {

        TextView address_item_name, address_item_mobile, address_item_address, address_item_isMoren;

    }

}
