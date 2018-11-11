package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.model.NoticeItem;
import com.zx.zhuangxiu.model.XiTongOne;
import com.zx.zhuangxiu.model.XiTongTwo;
import com.zx.zhuangxiu.utils.ToTime;

import java.util.List;

public class NoticeListViewAdapter extends BaseAdapter{


    private Context mContext;
    private List<XiTongOne.DataBean> mList;

    public NoticeListViewAdapter(Context mContext, List<XiTongOne.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.notice_listview_item, null);
            holder.notice_item_nian = (TextView) convertView.findViewById(R.id.notice_item_nian);
            holder.notice_item_title = (TextView) convertView.findViewById(R.id.notice_item_title);
            holder.notice_item_content = (TextView) convertView.findViewById(R.id.notice_item_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.notice_item_title.setText(mList.get(position).getWn_title());
            holder.notice_item_content.setText(mList.get(position).getWn_content());
            holder.notice_item_nian.setText(mList.get(position).getTime());
        }

        return convertView;
    }

    public class ViewHolder {

        TextView notice_item_nian, notice_item_title, notice_item_content;

    }

}
