package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.MyDongTaiSJOne;

import java.util.List;

public class MyDongTaiSJListAdapter extends BaseAdapter {
    private List<MyDongTaiSJOne> list;
    private Context context;

    public MyDongTaiSJListAdapter(List<MyDongTaiSJOne> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.my_dong_tai_sj_list_item, null);
            holder.textbiaoti = (TextView) view.findViewById(R.id.textbiaoti);//标题
            holder.textjiejian = (TextView) view.findViewById(R.id.textjiejian);//简介
            holder.textzongshu = (TextView) view.findViewById(R.id.textzongshu);//总数
            holder.textzhuangtai = (TextView) view.findViewById(R.id.textzhuangtai);//状态
            holder.textjiage = (TextView) view.findViewById(R.id.textjiage);//价格
            holder.imageview = (ImageView) view.findViewById(R.id.imageview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textbiaoti.setText(list.get(i).getTitle());//标题
        holder.textjiejian.setText("简介:"+list.get(i).getSummary());//简介
        holder.textzongshu.setText("总数量："+list.get(i).getTotalNum());//总数
        switch (list.get(i).getStatus()){
            case "1":
                holder.textzhuangtai.setText("状态：已发布");//状态
                break;
            case "0":
                holder.textzhuangtai.setText("状态：未发布");//状态
                break;
            default:
                break;
        }

        holder.textjiage.setText("上架单价：￥"+list.get(i).getPrice()); //价格
        if(list.get(i).getJskSysAnnex().size() != 0){
            Picasso.with(context).load(list.get(i).getJskSysAnnex().get(0).getFilePath()).fit().error(R.mipmap.logo_zhanwei).into(holder.imageview);
        }

        return view;
    }
    public class ViewHolder {
        ImageView imageview;
        TextView textbiaoti, textjiejian, textzongshu, textzhuangtai,textjiage;
    }
}
