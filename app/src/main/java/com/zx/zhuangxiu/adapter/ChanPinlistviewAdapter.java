package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import com.zx.zhuangxiu.activity.MapActivity;
import com.zx.zhuangxiu.activity.WXFXActivity;
import com.zx.zhuangxiu.model.SyProductOne;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

public class ChanPinlistviewAdapter extends BaseAdapter {
    private Context mContext;
    private List<SyProductOne.DataBean> mList;
    private AlertDialog pingLunDialog;
    private int userID;
    ViewHolder holder;
    private String msg = "";
    private int id;
    ItemClickListener listener;

    public ChanPinlistviewAdapter(Context mContext, List<SyProductOne.DataBean> mList, ItemClickListener listener) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.chanpin_fragment_item, null);
            holder.chanpin_item_title = (TextView) convertView.findViewById(R.id.chanpin_title);
            holder.chanpin_distance = (TextView) convertView.findViewById(R.id.chanpin_distance);
            holder.chanpin_item_xiaotitle = (TextView) convertView.findViewById(R.id.chanpin_xiaotitle);
            holder.chanpin_price = (TextView) convertView.findViewById(R.id.chanpin_price);
            holder.chanpin_item_address = (TextView) convertView.findViewById(R.id.chanpin_item_address);
            holder.chanpin_item_zan = (TextView) convertView.findViewById(R.id.chanpin_item_zan);
            holder.chanpin_img = (RoundImageView) convertView.findViewById(R.id.chanpin_img);
            holder.zan_img = (ImageView) convertView.findViewById(R.id.zan_img);
            holder.chanpin_item_commment = (ImageView) convertView.findViewById(R.id.chanpin_item_commment);
            holder.chanpin_item_fenxiang = (LinearLayout) convertView.findViewById(R.id.chanpin_item_fenxiang);
            holder.chanpin_rl = (RelativeLayout) convertView.findViewById(R.id.chanpin_rl);
            holder.pinglunll = (LinearLayout) convertView.findViewById(R.id.pinglunll);
            holder.dianzanll = (LinearLayout) convertView.findViewById(R.id.dianzanll);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList!=null) {
            holder.chanpin_item_title.setText("" + mList.get(position).getCdname());
            holder.chanpin_item_xiaotitle.setText("简介: " + mList.get(position).getSimple());
            holder.chanpin_distance.setText("距离你: " + mList.get(position).getDistance()+"米");
            holder.chanpin_price.setText("￥" + mList.get(position).getPrice());
            holder.chanpin_item_address.setText("" + mList.get(position).getAddress());
            holder.chanpin_item_zan.setText("" + mList.get(position).getUpnum());
            String imgUrl = mList.get(position).getPicture();
            if (!imgUrl.startsWith("http://")&&!imgUrl.startsWith("https://")){
                imgUrl=URLS.HTTP+imgUrl;
            }
            Picasso.with(mContext).load(imgUrl).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.chanpin_img);
        }

        holder.chanpin_item_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapActivity.class);
                intent.putExtra("map", mList.get(position).getAddress());
                mContext.startActivity(intent);
            }
        });

        //跳转产品的详情
        holder.chanpin_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG",mList.get(position).getId()+"");
                Intent intent = new Intent(mContext, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                bundle.putInt("shangpin",0);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        //该产品添加评论
        holder.pinglunll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, CommentsActivity.class);
                intent.putExtra("dataID",mList.get(position).getId());
                intent.putExtra("type",0);
                mContext.startActivity(intent);

//                commentAlert(mList.get(position).getPkId());
            }
        });

        //给该产品点赞
        holder.dianzanll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(position,holder.chanpin_item_zan );

            }
        });
        //分享
        holder.chanpin_item_fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WXFXActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
    public void update(int position, ListView mListView,int num){

        ((SyProductOne.DataBean) mList.get(position)).setUpnum(num);

        int visiblePosition = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(position - visiblePosition);
        TextView txt = (TextView) view.findViewById(R.id.chanpin_item_zan);
        txt.setText(num+"");

    }

    public class ViewHolder {

        RoundImageView chanpin_img;
        ImageView zan_img, chanpin_item_commment;
        TextView chanpin_item_title, chanpin_item_xiaotitle, chanpin_price, chanpin_item_address,
        chanpin_distance, chanpin_item_zan;
        LinearLayout chanpin_item_fenxiang, pinglunll, dianzanll;
        RelativeLayout chanpin_rl;
    }


}

/*

    //添加评论的弹出框
    public void commentAlert(final int id) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pinglun_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);

        final EditText et = (EditText) view.findViewById(R.id.pinglun_content);
        TextView quXiao = (TextView) view.findViewById(R.id.pinglun_cansol);
        TextView queDing = (TextView) view.findViewById(R.id.pinglun_queding);

        queDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pingLunDialog.dismiss();
                String content = et.getText().toString();
                addPlInfo(id, content);
            }
        });

        quXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pingLunDialog.dismiss();
            }
        });

        pingLunDialog = builder.create();
        pingLunDialog.setView(view, 0, 0, 0, 0);
        pingLunDialog.show();
    }*/


   /* //对接添加评论的接口
    private void addPlInfo(int id, String content) {
        String url = URLS.addPingLunShow(URLS.getUser_id(), id, 2, 1, content);
//            Log.d("xxx", "添加评论--------url--"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddPingLun>() {
            @Override
            public void onSuccess(AddPingLun response) {
                if (response.getId() == 0) {
                    String m1 = response.getMsg();
                    Toast.makeText(mContext, "" + m1, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "" + response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }*/