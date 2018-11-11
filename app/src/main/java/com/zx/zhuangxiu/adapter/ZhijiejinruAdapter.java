package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.FoundGoodsActivity;
import com.zx.zhuangxiu.model.ZhijiejinruBean;

import java.util.ArrayList;
import java.util.List;

public class ZhijiejinruAdapter extends BaseAdapter{

    private Context context;
    private List<ZhijiejinruBean.DataBean> zhijielist = new ArrayList<>();

    public ZhijiejinruAdapter(Context context, List<ZhijiejinruBean.DataBean> zhijielist) {
        this.context = context;
        this.zhijielist = zhijielist;
    }

    @Override
    public int getCount() {
        return zhijielist.size();
    }

    @Override
    public Object getItem(int i) {
        return zhijielist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.jinpai_item, null);
            holder.zhijie_item_details = (TextView) view.findViewById(R.id.jinpai_item_details);
            holder.zhijie_item_img = (ImageView) view.findViewById(R.id.jinpai_item_img);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Picasso.with(context)
                .load(URLS.HTTP+zhijielist.get(i).getImg_url())
                .error(R.mipmap.logo_zhanwei)
                .into(holder.zhijie_item_img);


        holder.zhijie_item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoundGoodsActivity.class);
                context.startActivity(intent);
            }
        });




        return view;
    }


    public class ViewHolder {

        ImageView zhijie_item_img;
        TextView zhijie_item_details;

    }
}
