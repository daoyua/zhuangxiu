package com.zx.zhuangxiu.adapter;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    Activity context;
    List<String> list;

    public ImageAdapter(Activity context, List<String> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_image, null);
        ImageView viewById = convertView.findViewById(R.id.img);
        String s = list.get(position);
        if (!TextUtils.isEmpty(s)) {
            if (s.startsWith("http://") || s.startsWith("https://")) {
                Picasso.with(context).load(list.get(position)).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(viewById);
            } else {
                Picasso.with(context).load(URLS.HTTP + list.get(position)).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(viewById);
            }
            //跳转详情界面
            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show(position);

                }
            });
        }

        return convertView;
    }

    public void show(int p) {
        View view = View.inflate(context, R.layout.image, null);
        ImageView imageView = view.findViewById(R.id.image);

        String imgUrls = list.get(p);
        if (!TextUtils.isEmpty(imgUrls)) {
            if (imgUrls.contains(",")) {
                String[] split = imgUrls.split(",");
                Log.d("GmfwListViewAdapter", "sp=====" + split[0]);
                if (split[0].startsWith("http://") || split[0].startsWith("https://")) {
                    Picasso.with(context).load(split[0]).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                } else {
                    Picasso.with(context).load(URLS.HTTP + split[0]).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                }
            } else {
                if (imgUrls.startsWith("http://") || imgUrls.startsWith("https://")) {
                    Picasso.with(context).load(imgUrls).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
                } else {
                    Picasso.with(context).load(URLS.HTTP + imgUrls).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                }
            }
        }


        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

      /*  AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.create();
        builder.show();*/


    }

}
