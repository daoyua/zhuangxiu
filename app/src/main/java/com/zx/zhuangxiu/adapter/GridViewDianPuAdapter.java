package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.DetailsActivity;
import com.zx.zhuangxiu.model.MyDianPu;

import java.util.List;

public class GridViewDianPuAdapter extends BaseAdapter {
    private Context context;
    private List<MyDianPu.DataBean> list;

    public GridViewDianPuAdapter(Context context, List<MyDianPu.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            holder.layout = (LinearLayout) view.findViewById(R.id.gridviewlayout);
            holder.text = (TextView) view.findViewById(R.id.text);
            holder.textpice = (TextView) view.findViewById(R.id.textpice);
            holder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(list.get(i).getCdname());
        holder.textpice.setText("￥"+list.get(i).getPrice());
        Picasso.with(context).load(URLS.HTTP+list.get(i).getPicture()).error(R.mipmap.logo_zhanwei).fit().into(holder.image);


        /**
         * 跳转商品详情
         */
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", list.get(i).getId());
                bundle.putInt("shangpin",0);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });



        return view;
    }
    public class ViewHolder {

        TextView text,textpice ;
        ImageView image;
        LinearLayout layout;

    }
}
