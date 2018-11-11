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
import com.zx.zhuangxiu.model.MyDingDanBean;
import com.zx.zhuangxiu.model.MyDongTaiGROne;

import java.util.List;

public class DingDanListViewAdapter extends BaseAdapter{


    private Context mContext;
    private List<MyDingDanBean.DataBean> mList;//复用实体类

    public DingDanListViewAdapter(Context mContext, List<MyDingDanBean.DataBean> mList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.my_dingdan_item, null);
            holder.textviewtitle = (TextView) convertView.findViewById(R.id.textbiaoti);
            holder.textviewcontext = (TextView) convertView.findViewById(R.id.textjiejian);
            holder.textviewdanjia = (TextView) convertView.findViewById(R.id.textzongshu);
            holder.textviewleixing = (TextView) convertView.findViewById(R.id.textzhuangtai);
            holder.textviewzhuangtai = (TextView) convertView.findViewById(R.id.textjiage);
            holder.imageview = (ImageView) convertView.findViewById(R.id.imageview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /*if (mList.size() != 0){

            holder.textviewtitle.setText(""+mList.get(position).get);

//        holder.textviewtitle.setText(mList.get(position).getJskPersonOrderDetail().get(0).getRecordName());
            holder.textviewcontext.setText("购买数量：×"+mList.get(position).getPayNum());
            holder.textviewdanjia.setText("总价格：￥"+mList.get(position).getPrice());
            switch (mList.get(position).getStatus()){
                case "0":
                    holder.textviewzhuangtai.setText("订单状态：未支付");
                    break;
                case "1":
                    if (mList.get(position).getType().equals("1")){
                        holder.textviewzhuangtai.setText("订单状态：线下支付");
                    }else {
                        holder.textviewzhuangtai.setText("订单状态：已支付");
                    }

                    break;
                case "2":
                    holder.textviewzhuangtai.setText("订单状态：已取消");
                    break;
                case "3":
                    holder.textviewzhuangtai.setText("订单状态：已删除");
                    break;
                default:
                    break;
            }
            switch (mList.get(position).getType()){

                case "1":
                    holder.textviewleixing.setText("类型：服务");
                    break;
                case "2":
                    holder.textviewleixing.setText("类型：产品");
                    break;
                default:
                    break;
            }
            if(mList.get(position).getJskPersonOrderDetail().size() != 0){
                Picasso.with(mContext).load(mList.get(position).getJskPersonOrderDetail().get(0).getRecordPhoto()).error(R.mipmap.logo_zhanwei).fit().into(holder.imageview);
            }

        }*/
        return convertView;
    }

    public class ViewHolder {
        ImageView imageview;
        TextView textviewtitle, textviewcontext, textviewdanjia, textviewleixing,textviewzhuangtai;
    }

}
