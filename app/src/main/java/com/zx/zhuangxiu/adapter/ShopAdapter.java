package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.OrderEntity;

import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private List<OrderEntity.DataBean.ListBean> list;
    private Context context;
    private ViewHolder myHolder;

    public ShopAdapter(List<OrderEntity.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        if (convertView == null) {
            myHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.listview_shop, null);
            myHolder.image = convertView.findViewById(R.id.image);
            myHolder.name = convertView.findViewById(R.id.name);
            myHolder.num = convertView.findViewById(R.id.num);
            myHolder.price = convertView.findViewById(R.id.price);
            convertView.setTag(myHolder);
        } else {
            myHolder = (ViewHolder) convertView.getTag();
        }
        myHolder.name.setText(list.get(position).getCdname());
        myHolder.num.setText(list.get(position).getCount()+"");
        myHolder.price.setText((list.get(position).getPrice()*list.get(position).getCount())+"元");
        Picasso.with(context).load(URLS.HTTP+list.get(position).getPicture()).error(R.mipmap.logo_zhanwei).fit().into(myHolder.image);

        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView name, num, price;
    }
}
