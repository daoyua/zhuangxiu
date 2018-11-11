package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    Context context;
    List<String> list;

    public ImageAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
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
        convertView=View.inflate(context, R.layout.item_image,null);
        ImageView viewById = convertView.findViewById(R.id.img);
        String s = list.get(position);
        if (!TextUtils.isEmpty(s)){
            if (s.startsWith("http://")||s.startsWith("https://")){
                Picasso.with(context).load(list.get(position)).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(viewById);
            }else {
                Picasso.with(context).load(URLS.HTTP+list.get(position)).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(viewById);
            }
        }

        return convertView;
    }
}
