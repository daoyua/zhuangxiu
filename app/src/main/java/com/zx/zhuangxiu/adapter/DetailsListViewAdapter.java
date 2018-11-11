package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.CommentsBean;

import java.util.List;

public class DetailsListViewAdapter extends BaseAdapter{

    private Context mContext;
    private List<CommentsBean.DataBean> mList;

    public DetailsListViewAdapter(Context mContext, List<CommentsBean.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.details_pinglun_item, null);
            holder.pinglun_item_title = (TextView) convertView.findViewById(R.id.pinglun_item_title);
            holder.pinglun_item_time = (TextView) convertView.findViewById(R.id.pinglun_item_time);
            holder.pinglun_item_content = (TextView) convertView.findViewById(R.id.pinglun_item_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList != null) {
            holder.pinglun_item_title.setText("评论人:"+mList.get(position).getParent().getNickname());
            holder.pinglun_item_time.setText("评论时间:"+mList.get(position).getParent().getAddTime());
            holder.pinglun_item_content.setText("评论内容:"+mList.get(position).getParent().getContent());
        }

        return convertView;
    }

    public class ViewHolder {

        TextView pinglun_item_title, pinglun_item_time, pinglun_item_content;

    }
}
