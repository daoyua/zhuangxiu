package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.MyDongTaiGR;

import java.util.List;

public class MyDongTaiGRListAdpter extends BaseAdapter {
    private List<MyDongTaiGR.DataBean> list;
    private Context context;

    public MyDongTaiGRListAdpter(List<MyDongTaiGR.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.activity_my_dong_tai_ge_ren, null);
            holder.textviewtitle = (TextView) view.findViewById(R.id.textbiaoti);
            holder.textviewdanjia = (TextView) view.findViewById(R.id.textzongshu);
            holder.imageview = (ImageView) view.findViewById(R.id.imageview);
            holder.textviewaddress = (TextView) view.findViewById(R.id.dongtaigeren_address);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(list.size() !=0){
            holder.textviewtitle.setText(list.get(i).getName());
            holder.textviewaddress.setText(list.get(i).getAddress());
            String imgUrl = list.get(i).getImgUrl();
            if (!TextUtils.isEmpty(imgUrl)){
                if (imgUrl.contains(",")){
                    String[] split = imgUrl.split(",");
                    if (!split[0].startsWith("http://") && !split[0].startsWith("https://")) {
                        split[0] = URLS.HTTP;
                    }
                    Picasso.with(context).load(split[0]).fit().error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.imageview);
                }
            }


        }
        return view;
    }
    public class ViewHolder {
        ImageView imageview;
        TextView textviewtitle, textviewcontext, textviewdanjia, textviewaddress;
    }
}
