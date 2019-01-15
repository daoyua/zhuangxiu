package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;

import java.util.List;

public class SpinnerSpAdapter extends BaseAdapter {
    private  Context context;
    private List<DaxiaofenleiBean.DataBean> mList;
    private Myholder myholder;

    public SpinnerSpAdapter(Context context, List<DaxiaofenleiBean.DataBean> mList) {
        this.context = context;
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
        if (convertView ==null){
            myholder = new Myholder();
            convertView=View.inflate(context,R.layout.item,null);
            myholder.textView = convertView.findViewById(R.id.text);
            convertView.setTag(myholder);
        }else {
            myholder = (Myholder) convertView.getTag();
        }
        myholder.textView.setText(mList.get(position).getCname());
        return convertView;
    }
    static class  Myholder{
        TextView textView;
    }
}
