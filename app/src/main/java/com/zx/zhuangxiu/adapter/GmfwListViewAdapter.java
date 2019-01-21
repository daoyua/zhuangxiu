package com.zx.zhuangxiu.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.MapActivity;
import com.zx.zhuangxiu.model.BusinessGmfwBean;
import com.zx.zhuangxiu.utils.MyUntils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;

public class GmfwListViewAdapter extends BaseAdapter {

    private Activity mContext;
    private List<BusinessGmfwBean.DataBean> mList = new ArrayList<>();

    public GmfwListViewAdapter(Activity mContext, List<BusinessGmfwBean.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gmfw_fragment_item, null);
            holder.gmfw_item_distance = (TextView) convertView.findViewById(R.id.gmfw_item_distance);
            holder.gmfw_item_title = (TextView) convertView.findViewById(R.id.gmfw_item_title);
            holder.gmfw_item_size = (TextView) convertView.findViewById(R.id.gmfw_item_size);
            holder.gmfw_item_dizhi = (TextView) convertView.findViewById(R.id.gmfw_item_dizhi);
            holder.gmfw_item_xiangmu = (TextView) convertView.findViewById(R.id.gmfw_item_xiangmu);
            holder.gmfw_item_yaoqiu = (TextView) convertView.findViewById(R.id.gmfw_item_yaoqiu);
            holder.gmfw_item_time = (TextView) convertView.findViewById(R.id.gmfw_item_time);
            holder.gmfw_item_wangongtime = (TextView) convertView.findViewById(R.id.gmfw_item_wangongtime);
            holder.gmfw_item_tuijian = (TextView) convertView.findViewById(R.id.gmfw_item_tuijian);
            holder.gmfw_item_youhaoyou = (TextView) convertView.findViewById(R.id.gmfw_item_youhaoyou);
            holder.gmfw_item_img = (ImageView) convertView.findViewById(R.id.gmfw_item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.gmfw_item_title.setText("" + mList.get(position).getName());
            holder.gmfw_item_size.setText("距离：" + mList.get(position).getArea() + "km");
            holder.gmfw_item_dizhi.setText("" + mList.get(position).getAddress());
//            holder.gmfw_item_xiangmu.setText("服务内容：" + mList.get(position).getName());
//            holder.gmfw_item_yaoqiu.setText("项目要求：" + mList.get(position).getRequires());
            holder.gmfw_item_xiangmu.setText("服务内容：" + mList.get(position).getRequires());
            holder.gmfw_item_yaoqiu.setText("项目要求：" + mList.get(position).getName());
            String   distance=  mList.get(position).getDistance();
            if(distance!=null){
                float  ssss=  Float.parseFloat(distance)/1000;
                holder.gmfw_item_distance.setText("距离你"+ssss+"千米");
            }else{
                holder.gmfw_item_distance.setText("距离你null千米");
            }
//            holder.gmfw_item_distance.setText("距离你：" + mList.get(position).getDistance()+"米");


            holder.gmfw_item_time.setText("开工时间 : " + demo2(mList.get(position).getStartTime()));
            holder.gmfw_item_wangongtime.setText("完工时间 : " + demo2(mList.get(position).getEndTime()));
//            holder.gmfw_item_tuijian.setText("");
//            holder.gmfw_item_youhaoyou.setText("");
            String imgUrls = mList.get(position).getImgUrl();
            Log.d("GmfwListViewAdapter", "img========" + imgUrls);
            if (!TextUtils.isEmpty(imgUrls)) {
                if (imgUrls.contains(",")) {
                    String[] split = imgUrls.split(",");
                    Log.d("GmfwListViewAdapter", "sp=====" + split[0]);
                    if (split[0].startsWith("http://") || split[0].startsWith("https://")) {
                        Picasso.with(mContext).load(split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.gmfw_item_img);
                    } else {
                        Picasso.with(mContext).load(URLS.HTTP + split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.gmfw_item_img);
                    }
                } else {
                    if (imgUrls.startsWith("http://") || imgUrls.startsWith("https://")) {
                        Picasso.with(mContext).load(imgUrls).error(R.mipmap.logo_zhanwei).fit().placeholder(R.mipmap.logo_zhanwei).into(holder.gmfw_item_img);
                    } else {
                        Picasso.with(mContext).load(URLS.HTTP + imgUrls).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.gmfw_item_img);
                    }
                }

            }


        }
        holder.gmfw_item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(mContext, position);
            }
        });


        holder.gmfw_item_youhaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RongIM.getInstance().startPrivateChat(mContext, mList.get(position).getUserId() + "", mList.get(position).getName());
            }
        });

        holder.gmfw_item_tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, WXFXActivity.class);
//                mContext.startActivity(intent);
                BusinessGmfwBean.DataBean dataBean = mList.get(position);
                MyUntils.call(mContext, dataBean.phone + "");
            }
        });
        holder.gmfw_item_dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapActivity.class);
                intent.putExtra("map", mList.get(position).getAddress());
                intent.putExtra("lat", mList.get(position).getLatitude());
                intent.putExtra("lon", mList.get(position).getLongitude());
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }

    public void show(Activity context, int p) {
        View view = View.inflate(context, R.layout.image, null);
        ImageView imageView = view.findViewById(R.id.image);
        String imgUrls = mList.get(p).getImgUrl();
        if (!TextUtils.isEmpty(imgUrls)) {
            if (imgUrls.contains(",")) {
                String[] split = imgUrls.split(",");
                Log.d("GmfwListViewAdapter", "sp=====" + split[0]);
                if (split[0].startsWith("http://") || split[0].startsWith("https://")) {
                    Picasso.with(mContext).load(split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
                } else {
                    Picasso.with(mContext).load(URLS.HTTP + split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
                }
            } else {
                if (imgUrls.startsWith("http://") || imgUrls.startsWith("https://")) {
                    Picasso.with(mContext).load(imgUrls).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
                } else {
                    Picasso.with(mContext).load(URLS.HTTP + imgUrls).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
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

    }


    private String demo2(long l) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(l);
    }

    public class ViewHolder {

        ImageView gmfw_item_img;
        TextView gmfw_item_title, gmfw_item_size, gmfw_item_dizhi, gmfw_item_xiangmu, gmfw_item_yaoqiu, gmfw_item_time,
                gmfw_item_wangongtime, gmfw_item_tuijian, gmfw_item_youhaoyou
                ,gmfw_item_distance;

    }
}
