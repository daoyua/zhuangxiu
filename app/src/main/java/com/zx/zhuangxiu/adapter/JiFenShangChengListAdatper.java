package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.DingDanHao;
import com.zx.zhuangxiu.model.JiFenDuiHuan;
import com.zx.zhuangxiu.model.JiFenShangChengBean;

import java.util.List;

public class JiFenShangChengListAdatper extends BaseAdapter {
    private List<JiFenShangChengBean.DataBean> list;
    private Context context;

    private String dingDanHao = "";

    public JiFenShangChengListAdatper(List<JiFenShangChengBean.DataBean> list, Context context) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.ji_fen_shang_cheng_list_item, null);
            holder.textbiaoti = (TextView) view.findViewById(R.id.textbiaoti);//标题
            holder.textjiejian = (TextView) view.findViewById(R.id.textjiejian);//简介
            holder.textshengyu = (TextView) view.findViewById(R.id.textzongshu);//剩余量，需要根据数据做减法
            holder.textok = (TextView) view.findViewById(R.id.textok);//确认兑换
            holder.textjiage = (TextView) view.findViewById(R.id.textzhuangtai);//所需积分
            holder.xiangxineirong = (TextView) view.findViewById(R.id.xiangxineirong);//详细内容
            holder.imageview = (ImageView) view.findViewById(R.id.imageview);//图片
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textbiaoti.setText("" + list.get(i).getCdname());//标题
//        holder.textjiejian.setText("简介: " + list.get(i).getSummary());//简介
//        int a = (list.get(i).getTotalNum()) - (list.get(i).getSaleNum());
//        holder.textshengyu.setText("剩余量: " + a);//剩余量，需要根据数据做减法
//        holder.textok.setOnClickListener(new View.OnClickListener() {//确定兑换，接接口
//            @Override
//            public void onClick(View view) {
//
//                getDingDanHao(list.get(i).getPkId());
//
//            }
//        });//确认兑换
//        holder.textjiage.setText("所需积分: " + list.get(i).getPrice());//所需积分
//        holder.xiangxineirong.setText("详情描述: " + list.get(i).getContent());//详细内容
//        if (list.get(i).getJskSysAnnex().size() != 0) {
//            Picasso.with(context).load(list.get(i).getJskSysAnnex().get(0).getFilePath()).error(R.mipmap.logo_zhanwei).fit().into(holder.imageview);
//        }

        return view;
    }

    //获取订单号
    private void getDingDanHao(int pkId) {

        String url = URLS.daShiQuanDetailsShow(URLS.getUser_id(), 3, pkId, 1);
        Log.d("hhh", "获取订单号-----url-------" + url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DingDanHao>() {
            @Override
            public void onSuccess(DingDanHao response) {
                if (response.getStatus() == 0) {
                    dingDanHao = response.getData();
                    Log.d("hhh", "获取订单号-----dingDanHao-------" + dingDanHao);
                    if (dingDanHao.equals("") || dingDanHao == null) {
                        Toast.makeText(context, "兑换积分失败！", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.d("hhh", "获取订单号-----进来duiHuanInfo-------");
                        duiHuanInfo(dingDanHao);
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    //积分支付
    private void duiHuanInfo(String dingDanHao) {
        String url = URLS.myJiFenDuiHuan(dingDanHao);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<JiFenDuiHuan>() {
            @Override
            public void onSuccess(JiFenDuiHuan response) {
                if(response.getStatus() == 0){
                    Toast.makeText(context, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public class ViewHolder {
        ImageView imageview;
        TextView textbiaoti, textjiejian, textshengyu, textok, textjiage, xiangxineirong;
    }
}
