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
import com.zx.zhuangxiu.activity.FoundWorkDetailsActivity;
import com.zx.zhuangxiu.activity.MapActivity;
import com.zx.zhuangxiu.activity.WXFXActivity;
import com.zx.zhuangxiu.model.ShouYeZgzOne;
import com.zx.zhuangxiu.utils.MyUntils;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

public class FoundWorkListViewAdapter extends BaseAdapter{


    private Context mContext;
    private List<ShouYeZgzOne.DataBean> mList;
    ItemClickListener listener;
    private int pkId;
    ViewHolder holder;

    public FoundWorkListViewAdapter(Context mContext, List<ShouYeZgzOne.DataBean> mList,ItemClickListener listener) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.found_work_item, null);
            holder.work_item_title = (TextView) convertView.findViewById(R.id.shouye_work_item_title);
            holder.work_item_xiaotitle = (TextView) convertView.findViewById(R.id.shouye_work_item_xiaotitle);
            holder.gongzuojingyan = (TextView) convertView.findViewById(R.id.shouye_work_item_gzjy);
            holder.xueli = (TextView) convertView.findViewById(R.id.shouye_work_item_xueli);
            holder.work_details = (TextView) convertView.findViewById(R.id.shouye_work_item_details);
            holder.work_item_address = (TextView) convertView.findViewById(R.id.shouye_work_item_dizhi);
            holder.shouye_work_item_tv = (TextView) convertView.findViewById(R.id.shouye_work_item_tv);

            holder.shouye_work_data = (TextView) convertView.findViewById(R.id.shouye_work_data);
            holder.jobRequire = (TextView) convertView.findViewById(R.id.shouye_work_item_require);
            holder.worktypes = (TextView) convertView.findViewById(R.id.shouye_work_item_worktypes);
            holder.treatment = (TextView) convertView.findViewById(R.id.shouye_work_item_treatment);
            holder.wages = (TextView) convertView.findViewById(R.id.shouye_work_item_wages);

            holder.work_img = (RoundImageView) convertView.findViewById(R.id.shouye_work_item_img);
            holder.shouye_work_item_ll = (LinearLayout) convertView.findViewById(R.id.shouye_work_item_ll);
            holder.dianzanlayout = convertView.findViewById(R.id.work_dianzanll);//点赞
            holder.dianzannum = convertView.findViewById(R.id.work_item_zan);//点赞数量更改
            holder.pinglunlayout = convertView.findViewById(R.id.work_pinglunll);//评论
            holder.fenxianglayout = convertView.findViewById(R.id.work_fenxiang);//分享
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            String addTime = mList.get(position).addTime;
            long l = Long.parseLong(addTime);
            String s = MyUntils.timeStamp2Date(l, "yyyy-MM-dd HH:mm:ss");
            holder.shouye_work_data.setText(s);
            holder.jobRequire.setText(mList.get(position).jobRequire);
            holder.worktypes.setText("工种："+mList.get(position).worktypes);
            holder.treatment.setText("工作待遇："+mList.get(position).treatment);
            holder.wages.setText("工资："+mList.get(position).wages);

            holder.work_item_title.setText(""+mList.get(position).getName());
            holder.xueli.setText("学历："+mList.get(position).getEducation());
            holder.work_item_xiaotitle.setText("工作地点："+mList.get(position).getAddress());
            holder.gongzuojingyan.setText("工作经验："+mList.get(position).getExperience());
            holder.work_details.setText("公司名称: "+mList.get(position).getSynopsis());
            holder.work_item_address.setText("项目地址: "+mList.get(position).getAddress());
            holder.dianzannum.setText(""+mList.get(position).getNumber());
            String imgUrl = mList.get(position).getImgUrl();
            Picasso.with(mContext).load(URLS.HTTP+imgUrl).error(R.mipmap.logo_zhanwei).fit().into(holder.work_img);
        }
        //跳转详情
        holder.shouye_work_item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoundWorkDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                bundle.putString("address", mList.get(position).getAddress());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        //跳转评论
        holder.pinglunlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CommentsActivity.class);
                intent.putExtra("dataID",mList.get(position).getId());
                intent.putExtra("type",1);
                mContext.startActivity(intent);
            }
        });
        //点赞功能
        holder.dianzanlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position,holder.dianzannum);
            }
        });
        //实现分享
        holder.fenxianglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WXFXActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.work_item_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MapActivity.class);
                intent.putExtra("map",mList.get(position).getAddress());
                mContext.startActivity(intent);
            }
        });




        return convertView;
    }

    public void update(int position, ListView mListView, int num){

        ((ShouYeZgzOne.DataBean) mList.get(position)).setNumber(num);

        int visiblePosition = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(position - visiblePosition);
        TextView txt = (TextView) view.findViewById(R.id.work_item_zan);
        txt.setText(num+"");

    }

    public class ViewHolder {

        RoundImageView work_img;
        TextView work_item_title, work_item_xiaotitle, work_details, work_item_address, shouye_work_item_tv,gongzuojingyan,xueli,dianzannum
                ,shouye_work_data,worktypes,telenumber,treatment,jobRequire,wages;
        LinearLayout shouye_work_item_ll,fenxianglayout,dianzanlayout,pinglunlayout;

    }

}
