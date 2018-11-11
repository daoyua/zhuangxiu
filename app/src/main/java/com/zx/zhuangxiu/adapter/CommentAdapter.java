package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.CommentsBean;
import com.zx.zhuangxiu.view.CircleImageView;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentsBean.DataBean> list;
    private MyHolder myHolder;

    public CommentAdapter(Context context, List<CommentsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            myHolder = new MyHolder();
            convertView = View.inflate(context, R.layout.listview_comments , null);
            myHolder.image=   convertView.findViewById(R.id.image);
            myHolder.name=convertView.findViewById(R.id.name);
            myHolder.time=convertView.findViewById(R.id.time);
            myHolder.content=convertView.findViewById(R.id.content);
            convertView.setTag(myHolder);
        }else {
            myHolder= (MyHolder) convertView.getTag();
        }
        myHolder.time.setText(list.get(position).getParent().getAddTime());
        myHolder.name.setText(list.get(position).getParent().getNickname());
        myHolder.content.setText(list.get(position).getParent().getContent());
        Picasso.with(context).load(URLS.HTTP+list.get(position).getParent().getUserUrl()).error(R.mipmap.logo_zhanwei).fit().into(myHolder.image);
        return convertView;
    }

    class MyHolder{
        CircleImageView image;
        TextView name,time,content;
    }
}
