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
import com.zx.zhuangxiu.model.MyDingDanQYandSJOne;

import java.util.List;

public class DingDanQYandSJAdapter extends BaseAdapter {
    private Context context;
    private List<MyDingDanQYandSJOne> list;

    public DingDanQYandSJAdapter(Context context, List<MyDingDanQYandSJOne> list) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.my_ding_dan_qy_and_sj_list_item, null);
            holder.textbiaoti = (TextView) view.findViewById(R.id.textbiaoti);
            holder.textshuliang = (TextView) view.findViewById(R.id.textshuliang);
            holder.textzongjiage = (TextView) view.findViewById(R.id.textzongjiage);
            holder.textleixing = (TextView) view.findViewById(R.id.textleixing);
            holder.textzhifuzhuangtai = (TextView) view.findViewById(R.id.textzhifuzhuangtai);
            holder.kuaidihao = (TextView) view.findViewById(R.id.kuaidihao);
            holder.imageview = (ImageView) view.findViewById(R.id.imageview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if(list.get(i).getJskPersonOrderDetail().size() != 0){
            holder.textbiaoti.setText(list.get(i).getJskPersonOrderDetail().get(0).getRecordName());
            holder.textshuliang.setText("购买数量：" + list.get(i).getJskPersonOrderDetail().get(0).getPayNum());
            Picasso.with(context).load(list.get(i).getJskPersonOrderDetail().get(0).getRecordPhoto()).error(R.mipmap.logo_zhanwei).fit().into( holder.imageview);
        }

        holder.textzongjiage.setText("总价格：" + list.get(i).getPrice());
        switch (list.get(i).getType()) {
            case "1":
                holder.textleixing.setText("类型：服务");
                break;
            case "2":
                holder.textleixing.setText("类型：产品");
                break;
            default:
                break;
        }
        switch (list.get(i).getStatus()) {
            case "0":
                holder.textzhifuzhuangtai.setText("支付状态：未支付");
                break;
            case "1":
                if (list.get(i).getType().equals("1")){
                    holder.textzhifuzhuangtai.setText("支付状态：线下支付");
                }else {
                    holder.textzhifuzhuangtai.setText("支付状态：已支付");
                }
                break;
            case "2":
                holder.textzhifuzhuangtai.setText("支付状态：已取消");
                break;
            case "3":
                holder.textzhifuzhuangtai.setText("支付状态：已删除");
                break;
            default:
                break;
        }
        if (!list.get(i).getExpress().equals("0")&&list.get(i).getExpress()!=null){
            holder.kuaidihao.setText("此订单已处理："+list.get(i).getExpress());
        }else {
            holder.kuaidihao.setText("您还未处理此订单，点击处理吧");
        }

        return view;
    }

    public class ViewHolder {
        ImageView imageview;
        TextView textbiaoti, textshuliang, textzongjiage, textleixing, textzhifuzhuangtai, kuaidihao;
    }

}
