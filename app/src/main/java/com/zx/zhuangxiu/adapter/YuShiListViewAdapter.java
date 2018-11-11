package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.CommentsActivity;
import com.zx.zhuangxiu.activity.DetailsActivity;
import com.zx.zhuangxiu.activity.WXFXActivity;
import com.zx.zhuangxiu.model.SyProductOne;

import java.util.List;

public class YuShiListViewAdapter extends BaseAdapter{


    private Context mContext;
    private List<SyProductOne.DataBean> mList;
    private AlertDialog pingLunDialog;

    private String msg = "";
    private int zan = 0;
    ViewHolder holder;
    ItemClickListener listener;

    public YuShiListViewAdapter(Context mContext, List<SyProductOne.DataBean> mList,ItemClickListener listener) {
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
         holder = new ViewHolder();
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.yushi_listview_item, null);
            holder.yushi_item_title = (TextView) convertView.findViewById(R.id.yushi_item_title);
            holder.yushi_item_xiaotitle = (TextView) convertView.findViewById(R.id.yushi_item_xiaotitle);
            holder.yushi_item_price = (TextView) convertView.findViewById(R.id.yushi_item_price);
            holder.yushi_item_address = (TextView) convertView.findViewById(R.id.yushi_item_address);
            holder.yushi_item_img = (ImageView) convertView.findViewById(R.id.yushi_item_img);
            holder.yushi_item_pl = (LinearLayout)convertView.findViewById(R.id.yushi_item_pl);
            holder.yushi_item_zan = (TextView) convertView.findViewById(R.id.yushi_item_zan);
            holder.yushi_ll_zan = (LinearLayout) convertView.findViewById(R.id.yushi_ll_zan);
            holder.img_item_zan = (ImageView)convertView.findViewById(R.id.img_item_zan);
            holder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);
            holder.fenxianglayout = (LinearLayout) convertView.findViewById(R.id.homeyushi_fenxiang);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.yushi_item_title.setText(""+mList.get(position).getCdname());
            holder.yushi_item_xiaotitle.setText("简介: "+mList.get(position).getSimple());
            holder.yushi_item_price.setText("￥"+mList.get(position).getPrice());
            holder.yushi_item_zan.setText(""+mList.get(position).getUpnum());
            holder.yushi_item_address.setText(""+mList.get(position).getPrice());
            String imgUrl = mList.get(position).getPicture();
            Picasso.with(mContext).load(URLS.HTTP+imgUrl).error(R.mipmap.logo_zhanwei).fit().into(holder.yushi_item_img);
//           Log.d("xxx", "直接进入-----mList----"+mList.get(position).getPkId());
        }

        //添加评论
        holder.yushi_item_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, CommentsActivity.class);
                intent.putExtra("dataID",mList.get(position).getId());
                intent.putExtra("type",0);
                mContext.startActivity(intent);
            }
        });

        //跳转详情界面
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                bundle.putInt("shangpin",0);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        //点赞

        holder.yushi_ll_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(position,holder.yushi_item_zan );
            }
        });

        //分享
        holder.fenxianglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WXFXActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }


    public void update(int position, ListView mListView, int num){

        ((SyProductOne.DataBean) mList.get(position)).setUpnum(num);

        int visiblePosition = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(position - visiblePosition);
        TextView txt = (TextView) view.findViewById(R.id.yushi_item_zan);
        txt.setText(num+"");

    }

    public class ViewHolder {

        ImageView yushi_item_img, img_item_zan;
        TextView yushi_item_title, yushi_item_xiaotitle, yushi_item_price, yushi_item_address, yushi_item_zan;
        LinearLayout yushi_ll_zan,fenxianglayout,yushi_item_pl;
        RelativeLayout rl;

    }


}
