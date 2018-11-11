package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.JinEMxTwo;
import com.zx.zhuangxiu.model.MingXiBean;
import com.zx.zhuangxiu.model.SyProductTwo;
import com.zx.zhuangxiu.utils.ToTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MingXiAdapter extends BaseAdapter{

    private Context mContext;
    private List<MingXiBean.DataBean> mList;

    public MingXiAdapter(Context mContext, List<MingXiBean.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mingxi_item, null);
            holder.mingxi_item_times = (TextView) convertView.findViewById(R.id.mingxi_item_time);
            holder.mingxi_item_titles = (TextView) convertView.findViewById(R.id.mingxi_item_title);
            holder.mingxi_item_moneys = (TextView) convertView.findViewById(R.id.mingxi_item_money);
            holder.mingxi_item_contents = (TextView) convertView.findViewById(R.id.mingxi_item_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (mList.size() != 0) {
            holder.mingxi_item_times.setText(mList.get(position).getTime());

            if (mList.get(position).getStatus()==1){
                holder.mingxi_item_titles.setText(mList.get(position).getRemark());
                holder.mingxi_item_moneys.setTextColor(mContext.getResources().getColor(R.color.green));


            }else {
                holder.mingxi_item_titles.setText(mList.get(position).getRemark());
                holder.mingxi_item_moneys.setTextColor(mContext.getResources().getColor(R.color.red));

            }

//            holder.mingxi_item_contents.setText(mList.get(position).getStatus());
            holder.mingxi_item_contents.setText("7`21天到账");
        }

        return convertView;
    }



    public class ViewHolder {

        TextView mingxi_item_times, mingxi_item_titles, mingxi_item_moneys, mingxi_item_contents;

    }

}
