package com.zx.zhuangxiu.adapter;

import android.app.Activity;
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
import com.zx.zhuangxiu.model.BusinessCgspBean;
import com.zx.zhuangxiu.utils.MyUntils;
import com.zx.zhuangxiu.utils.ToTime;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

import io.rong.imkit.RongIM;

public class CgxpListViewAdapter extends BaseAdapter {
    private Activity mContext;
    private List<BusinessCgspBean.DataBean> mList;

    public CgxpListViewAdapter(Activity mContext, List<BusinessCgspBean.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cgxp_fragment_item, null);
            holder.cgxp_item_title = (TextView) convertView.findViewById(R.id.cgxp_item_title);
            holder.cgxp_item_yaoqiu = (TextView) convertView.findViewById(R.id.cgxp_item_yaoqiu);
            holder.cgxp_item_yongtu = (TextView) convertView.findViewById(R.id.cgxp_item_yongtu);
            holder.cgxp_item_price = (TextView) convertView.findViewById(R.id.cgxp_item_price);
            holder.cgxp_item_nums = (TextView) convertView.findViewById(R.id.cgxp_item_nums);
            holder.cgxp_item_riqi = (TextView) convertView.findViewById(R.id.cgxp_item_riqi);
            holder.cgxp_item_tuijian = (TextView) convertView.findViewById(R.id.cgxp_item_tuijian);
            holder.cgxp_item_youhaoyou = (TextView) convertView.findViewById(R.id.cgxp_item_youhaoyou);
            holder.cgxp_item_img = (RoundImageView) convertView.findViewById(R.id.cgxp_item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.cgxp_item_title.setText("" + mList.get(position).getGoodsname());
            holder.cgxp_item_yaoqiu.setText("产品简介:" + mList.get(position).getGoodsinfo());
            final long phone = mList.get(position).getPhone();

//            holder.cgxp_item_yongtu.setText("电话:" +333333);
            holder.cgxp_item_yongtu.setText("电话:" +phone);
            holder.cgxp_item_price.setText("理想价位:￥" + mList.get(position).getPrice());
            holder.cgxp_item_nums.setText("数量:" + mList.get(position).getNum() + "个");
            String riqi = ToTime.getDateTimeFromMillisecond(mList.get(position).getEndtime());
            holder.cgxp_item_riqi.setText("截止有效期:" + riqi);
            String imgUrls = mList.get(position).getImg();
            if (!TextUtils.isEmpty(imgUrls)){
                if (imgUrls.contains(",")){
                    String[] split = imgUrls.split(",");
                    if (split[0].startsWith("http://")||split[0].startsWith("https://")){
                        Picasso.with(mContext).load( split[0]).error(R.mipmap.logo_zhanwei).fit().into(holder.cgxp_item_img);
                    }else {
                        Picasso.with(mContext).load(URLS.HTTP + split[0]).error(R.mipmap.logo_zhanwei).fit().into(holder.cgxp_item_img);
                    }
                }else {
                    if (imgUrls.startsWith("http://")||imgUrls.startsWith("https://")){
                        Picasso.with(mContext).load( imgUrls).error(R.mipmap.logo_zhanwei).fit().into(holder.cgxp_item_img);
                    }else {
                        Picasso.with(mContext).load(URLS.HTTP + imgUrls).error(R.mipmap.logo_zhanwei).fit().into(holder.cgxp_item_img);
                    }
                }

            }
            //打电话
            holder.cgxp_item_tuijian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyUntils.call(mContext,phone+"");
                }
            });

        }

        //跳转详情界面
        holder.cgxp_item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(mContext,position);

            }
        });
// 购买商品-----跳转详情界面
        holder.cgxp_item_youhaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RongIM.getInstance().startPrivateChat(mContext, mList.get(position).getUserId() + "", "客服");
            }
        });

        //跳转分享微信
//        holder.cgxp_item_tuijian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, WXFXActivity.class);
//                mContext.startActivity(intent);
//            }
//        });

        return convertView;
    }

    public void show(Activity context,int p) {
        View view = View.inflate(mContext, R.layout.image, null);
        ImageView imageView = view.findViewById(R.id.image);

        String imgUrls = mList.get(p).getImg();
        if (!TextUtils.isEmpty(imgUrls)){
            if (imgUrls.contains(",")){
                String[] split = imgUrls.split(",");
                Log.d("GmfwListViewAdapter","sp====="+ split[0]);
                if (split[0].startsWith("http://")||split[0].startsWith("https://")){
                    Picasso.with(mContext).load( split[0]).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                }else {
                    Picasso.with(mContext).load(URLS.HTTP + split[0]).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                }
            }else {
                if (imgUrls.startsWith("http://")||imgUrls.startsWith("https://")){
                    Picasso.with(mContext).load( imgUrls).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
                }else {
                    Picasso.with(mContext).load(URLS.HTTP + imgUrls).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                }
            }
        }



        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
            }
        });

      /*  AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.create();
        builder.show();*/


    }

    public class ViewHolder {

        RoundImageView cgxp_item_img;
        TextView cgxp_item_title, cgxp_item_yaoqiu, cgxp_item_yongtu, cgxp_item_price, cgxp_item_nums, cgxp_item_riqi, cgxp_item_tuijian, cgxp_item_youhaoyou;

    }


}
