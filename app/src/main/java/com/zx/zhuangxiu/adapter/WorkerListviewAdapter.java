package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.CommentsActivity;
import com.zx.zhuangxiu.activity.FoundWorkerDetailsActivity;
import com.zx.zhuangxiu.activity.MapActivity;
import com.zx.zhuangxiu.activity.WXFXActivity;
import com.zx.zhuangxiu.model.ShouYeZgrOne;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

public class WorkerListviewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShouYeZgrOne.DataBean> mList;

    ViewHolder holder ;
    ItemClickListener listener;
    public WorkerListviewAdapter(Context mContext, List<ShouYeZgrOne.DataBean> mList,ItemClickListener listener) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        this.listener = listener;
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

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.worker_listview_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.worker_item_name);
            holder.age = (TextView) convertView.findViewById(R.id.worker_item_nianling);
            holder.sex = (TextView) convertView.findViewById(R.id.worker_item_xingbie);
            holder.ruwangAge = (TextView) convertView.findViewById(R.id.worker_item_gongling);
//            holder.dizhi = (TextView) convertView.findViewById(R.id.worker_item_suozaidi);
            holder.kongjian = (TextView) convertView.findViewById(R.id.worker_item_gerenkongjian);
            holder.address = (TextView) convertView.findViewById(R.id.worker_item_dingweiaddress);

            holder.workdlist_disdance = (TextView) convertView.findViewById(R.id.workdlist_disdance);
            holder.shouye_worker_data = (TextView) convertView.findViewById(R.id.shouye_worker_data);

            holder.worker_item_ll = (LinearLayout) convertView.findViewById(R.id.worker_item_ll);
            holder.worker_item_touxiang = (RoundImageView)convertView.findViewById(R.id.worker_item_touxiang);
            holder.work_type=convertView.findViewById(R.id.work_types);
            holder.dianzanlayout = convertView.findViewById(R.id.worker_dianzanll);
            holder.pinglunlayout = convertView.findViewById(R.id.worker_pinglunll);
            holder.fenxianglayout = convertView.findViewById(R.id.worker_fenxiang);
            holder.dianzannum = convertView.findViewById(R.id.workers_item_zan);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {

            ShouYeZgrOne.DataBean collectList = mList.get(position);
            holder.name.setText("" + collectList.getRealname());
            holder.age.setText(" " + collectList.getAge()+ "岁");
//            holder.dizhi.setText("" + collectList.getAddress());
            holder.kongjian.setText("" + collectList.getSpace());
            holder.address.setText("" + collectList.getAddress());
            holder.sex.setText(collectList.getSex());
            holder.dianzannum.setText(""+collectList.getNumber());
            holder.work_type.setText(collectList.getWorkType());
            holder.workdlist_disdance.setText("距离你"+collectList.getDistance()+"米");

            holder.shouye_worker_data.setText(collectList.getRegistrationDate());

            String imageurl = collectList.getUserUrl();
            if (!imageurl.startsWith("http://")&&!imageurl.startsWith("https://")){
                imageurl=URLS.HTTP+imageurl;
            }
            Picasso.with(mContext).load( imageurl).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).into(holder.worker_item_touxiang);
            holder.ruwangAge.setText(collectList.getYearNum()+"年");
        }

        /**
         * 地址
         */
        holder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MapActivity.class);
                intent.putExtra("map",mList.get(position).getAddress());
                mContext.startActivity(intent);
            }
        });

        /**
         * 工人详情
         */
        holder.worker_item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoundWorkerDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getUserId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        /**
         * 评论
         */
        holder.pinglunlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CommentsActivity.class);
                intent.putExtra("dataID",mList.get(position).getUserId());
                intent.putExtra("type",1);
                mContext.startActivity(intent);
            }
        });

        /**
         * 分享
         */
        holder.fenxianglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WXFXActivity.class);
                mContext.startActivity(intent);
            }
        });

        /**
         * 点赞
         */
        holder.dianzanlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position,holder.dianzannum);
            }
        });



        return convertView;
    }

    public void update(int position, ListView mListView, int num){

        ((ShouYeZgrOne.DataBean) mList.get(position)).setNumber(num);

        int visiblePosition = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(position - visiblePosition);
        TextView txt = (TextView) view.findViewById(R.id.workers_item_zan);
        txt.setText(num+"");

    }

    public class ViewHolder {
        RoundImageView worker_item_touxiang;
        TextView name, age, sex, ruwangAge, kongjian, address,dianzannum ,work_type
                ,shouye_worker_data
                ,workdlist_disdance;//, dizhi
        LinearLayout worker_item_ll,dianzanlayout,pinglunlayout,fenxianglayout;

    }

}
