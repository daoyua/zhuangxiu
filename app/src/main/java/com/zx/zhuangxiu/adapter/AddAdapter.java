package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.ShangpinguigeBean;
import com.zx.zhuangxiu.myinterface.GridClick;

import org.w3c.dom.Text;

import java.util.List;

public class AddAdapter extends BaseAdapter {
    Context context;
    List<ShangpinguigeBean.DataBean.ItemlistBean> list;
    private TextView text;

    private int lastPosition=-1;
    public AddAdapter(Context context, List<ShangpinguigeBean.DataBean.ItemlistBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setSeclection(int position) {
        if (lastPosition!=position){
            lastPosition = position;
        }else {
            lastPosition=-1;
        }

    }
    public int getSeclection(){
        return lastPosition;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item, null);
        text = convertView.findViewById(R.id.text);
        text.setText(list.get(position).getName());
        if (lastPosition == position) {//最后选择的位置
            text.setTextColor(context.getResources().getColor(R.color.green));
        } else {
            text.setTextColor(context.getResources().getColor(R.color.black));
        }

        return convertView;
    }

}
