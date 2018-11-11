package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zx.zhuangxiu.R;

import java.util.List;

public class PersonGridViewAdapter extends BaseAdapter{


    private Context mContext;
    private List<String> mList;

    public PersonGridViewAdapter(Context mContext, List<String> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.person_gridview_item, null);
            holder.person_item_tv = (TextView) convertView.findViewById(R.id.person_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (this.mList != null) {
            holder.person_item_tv.setText(mList.get(position));
        }

        return convertView;
    }

    public class ViewHolder {

        TextView person_item_tv;
    }


}
