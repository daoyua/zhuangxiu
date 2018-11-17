package com.zx.zhuangxiu.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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
import com.zx.zhuangxiu.model.BusinessJmhzBean;
import com.zx.zhuangxiu.utils.MyUntils;
import com.zx.zhuangxiu.utils.ToTime;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

import io.rong.imkit.RongIM;

public class JmhzListViewAdapter extends BaseAdapter {


    private Activity mContext;
    private List<BusinessJmhzBean.DataBean> mList;

    private AlertDialog keFuDialog;

    public JmhzListViewAdapter(Activity mContext, List<BusinessJmhzBean.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.jmhz_fragment_item, null);
            holder.gmhz_item_phone = (TextView) convertView.findViewById(R.id.gmhz_item_phone);
            holder.gmfw_item_siliao = (TextView) convertView.findViewById(R.id.gmfw_item_siliao);
            holder.jmhz_item_title = (TextView) convertView.findViewById(R.id.jmhz_item_title);
            holder.jmhz_item_dizhi = (TextView) convertView.findViewById(R.id.jmhz_item_dizhi);
            holder.jmhz_item_xiangqing = (TextView) convertView.findViewById(R.id.jmhz_item_xiangqing);
            holder.jmhz_item_yaoqiu = (TextView) convertView.findViewById(R.id.jmhz_item_yaoqiu);
            holder.jmhz_item_qixian = (TextView) convertView.findViewById(R.id.jmhz_item_qixian);
            holder.jmhz_item_nums = (TextView) convertView.findViewById(R.id.jmhz_item_nums);
            holder.jmhz_item_hezuo = (TextView) convertView.findViewById(R.id.jmhz_item_hezuo);
            holder.jmhz_item_img = (RoundImageView) convertView.findViewById(R.id.jmhz_item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.jmhz_item_title.setText("" + mList.get(position).getCompanyname());
            holder.jmhz_item_dizhi.setText(" " + mList.get(position).getAddress());
            holder.jmhz_item_xiangqing.setText("详情介绍:" + mList.get(position).getInfo());
            holder.jmhz_item_yaoqiu.setText("合作要求:" + mList.get(position).getRequire());
            String time = ToTime.getDateTimeFromMillisecond(mList.get(position).getCreattime());
            holder.jmhz_item_qixian.setText("创建时间:" + time);
            holder.jmhz_item_nums.setText("联系电话:" + mList.get(position).getNum());
            String imgUrls = mList.get(position).getBusinessimg();
            if (!TextUtils.isEmpty(imgUrls)) {
                if (imgUrls.contains(",")) {
                    String[] split = imgUrls.split(",");
                    if (split[0].startsWith("http://") || split[0].startsWith("https://")) {
                        Picasso.with(mContext).load(split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.jmhz_item_img);
                    } else {
                        Picasso.with(mContext).load(URLS.HTTP + split[0]).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(holder.jmhz_item_img);
                    }
                } else {
                    if (imgUrls.startsWith("http://") || imgUrls.startsWith("https://")) {
                        Picasso.with(mContext).load(imgUrls).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(holder.jmhz_item_img);
                    } else {
                        Picasso.with(mContext).load(URLS.HTTP + imgUrls).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(holder.jmhz_item_img);
                    }
                }

            }


            holder.jmhz_item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show(mContext, position);
                }
            });
            holder.gmhz_item_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long phone = mList.get(position).getNum();
                    MyUntils.call(mContext, phone + "");
                }
            });
            holder.gmfw_item_siliao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RongIM.getInstance().startPrivateChat(mContext, mList.get(position).getUserId() + "", mList.get(position).getCompanyname());
                }
            });
//            final String m = mList.get(position).;
////            Log.d("xxx", "加盟合作--电话======="+m);
//
//            holder.jmhz_item_hezuo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    showKefuDialog(m);
//                }
//            });
        }
        return convertView;
    }

    public void show(Activity context, int p) {
        View view = View.inflate(context, R.layout.image, null);
        ImageView imageView = view.findViewById(R.id.image);
        String imgUrls = mList.get(p).getBusinessimg();
        if (!TextUtils.isEmpty(imgUrls)) {
            if (imgUrls.contains(",")) {
                String[] split = imgUrls.split(",");
                Log.d("GmfwListViewAdapter", "sp=====" + split[0]);
                if (split[0].startsWith("http://") || split[0].startsWith("https://")) {
                    Picasso.with(mContext).load(split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(imageView);
                } else {
                    Picasso.with(mContext).load(URLS.HTTP + split[0]).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                }
            } else {
                if (imgUrls.startsWith("http://") || imgUrls.startsWith("https://")) {
                    Picasso.with(mContext).load(imgUrls).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
                } else {
                    Picasso.with(mContext).load(URLS.HTTP + imgUrls).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(imageView);
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

    private void showKefuDialog(final String mobile) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.mypager_kefu_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.kefu_dialog_quxiao);
        TextView tv_hujiao = (TextView) view.findViewById(R.id.kefu_dialog_hujiao);
        TextView tv_dianhua = (TextView) view.findViewById(R.id.kefu_dialog_dianhua);

        tv_dianhua.setText("" + mobile);

        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keFuDialog.dismiss();
            }
        });
        tv_hujiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keFuDialog.dismiss();
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:15923400098"));
//                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);


            }
        });

        keFuDialog = builder.create();
        keFuDialog.setView(view, 0, 0, 0, 0);
        keFuDialog.show();
    }

    public class ViewHolder {

        RoundImageView jmhz_item_img;
        TextView jmhz_item_title, jmhz_item_dizhi, jmhz_item_xiangqing, jmhz_item_yaoqiu, jmhz_item_qixian, jmhz_item_nums, gmhz_item_phone, gmfw_item_siliao, jmhz_item_hezuo;

    }
}
