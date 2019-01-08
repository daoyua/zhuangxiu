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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.CommentsActivity;
import com.zx.zhuangxiu.activity.FoundFuWuDetailsActivity;
import com.zx.zhuangxiu.activity.MapActivity;
import com.zx.zhuangxiu.activity.WXFXActivity;
import com.zx.zhuangxiu.model.AddPingLun;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.SyFuWuss;
import com.zx.zhuangxiu.utils.ToastUtil;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

import okhttp3.FormBody;

public class FuWuListViewAdapter extends BaseAdapter{

    private Context mContext;
    private List<SyFuWuss.DataBean.PageBean> mList;
    private AlertDialog pingLunDialog;
    ViewHolder holder;

    private int pkId = 1;
    private String msg;
    private int zan = 0;
    private int userID;
    private int id;
    ItemClickListener listener;

    public FuWuListViewAdapter(Context mContext, List<SyFuWuss.DataBean.PageBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public FuWuListViewAdapter(Context mContext, List<SyFuWuss.DataBean.PageBean> mList, int userID, ItemClickListener listener) {
        super();
        this.mContext = mContext;
        this.mList = mList;
        this.userID = userID;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fuwu_fragment_item, null);
            holder.fuwu_item_title = (TextView) convertView.findViewById(R.id.fuwu_title);
            holder.fuwu_item_xiaotitle = (TextView) convertView.findViewById(R.id.fuwu_xiaotitle);
            holder.fuwu_item_address = (TextView) convertView.findViewById(R.id.fuwu_item_address);
            holder.fuwu_item_zanTv = (TextView)convertView.findViewById(R.id.fuwu_item_zanTv) ;
            holder.shouye_fuwu_distance = (TextView)convertView.findViewById(R.id.shouye_fuwu_distance) ;
            holder.shouye_fuwu_data = (TextView)convertView.findViewById(R.id.shouye_fuwu_data) ;
            holder.fuwu_img = (RoundImageView) convertView.findViewById(R.id.fuwu_img);
            holder.fuwu_item_zan = (ImageView)convertView.findViewById(R.id.fuwu_item_zan);
            holder.fuwu_item_pl = (ImageView)convertView.findViewById(R.id.fuwu_item_pl);
            holder.fuwu_rl = (RelativeLayout) convertView.findViewById(R.id.fuwu_rl);
            holder.dianzanll=(LinearLayout)convertView.findViewById(R.id.dianzanll);
            holder.pinglunll=(LinearLayout)convertView.findViewById(R.id.pinglunll);
            holder.fenxiangll=(LinearLayout)convertView.findViewById(R.id.fenxiangll);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            holder.fuwu_item_title.setText(""+mList.get(position).getName());
            holder.fuwu_item_xiaotitle.setText("服务内容: "+mList.get(position).getRequires());
            holder.shouye_fuwu_distance.setText("距离你"+mList.get(position).getDistance()+"米");
            holder.fuwu_item_zanTv.setText(""+mList.get(position).getThumbsup());
            holder.fuwu_item_address.setText("地址: "+mList.get(position).getAddress());
            holder.shouye_fuwu_data.setText(mList.get(position).addTime);
            id = mList.get(position).getId();
            mList.get(position).getThumbsup();
            String imgUrl = mList.get(position).getImgUrl();
            String[] split = imgUrl.split(",");
            Picasso.with(mContext).load(URLS.HTTP+split[0]).error(R.mipmap.logo_zhanwei).fit().into(holder.fuwu_img);

        }

        //添加服务评论
        holder.pinglunll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                commentAlert(mList.get(position).getPkId());

                Intent intent=new Intent(mContext, CommentsActivity.class);
                intent.putExtra("dataID",mList.get(position).getId());
                intent.putExtra("type",1);
                mContext.startActivity(intent);
            }
        });

        holder.fuwu_item_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(mContext, MapActivity.class);
                intent.putExtra("map",mList.get(position).getAddress());
                mContext.startActivity(intent);
            }
        });

        //跳转详情界面
        holder.fuwu_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoundFuWuDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


        //点赞
        holder.dianzanll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(position,holder.fuwu_item_zanTv);
            }
        });
        //分享
        holder.fenxiangll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, WXFXActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }


    public void update(int position, ListView mListView, int num){

        ((SyFuWuss.DataBean.PageBean) mList.get(position)).setThumbsup(num);

        int visiblePosition = mListView.getFirstVisiblePosition();
        View view = mListView.getChildAt(position - visiblePosition);
        TextView txt = (TextView) view.findViewById(R.id.fuwu_item_zanTv);
        txt.setText(num+"");

    }

    //添加评论的弹出框
    public void commentAlert(final int id){
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
    }

    //对接添加评论的接口
    private void addPlInfo(int id, String content) {
        String url = URLS.addPingLunShow(URLS.getUser_id(), id, 1, 1, content);
//            Log.d("xxx", "添加评论--------url--"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddPingLun>() {
            @Override
            public void onSuccess(AddPingLun response) {
                if(response.getId() == 0){
                    String m1 = response.getMsg();
                    Toast.makeText(mContext, ""+m1, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }



    private void dianZanInfo() {
        String url = URLS.dianzan();
        FormBody formBody=new FormBody.Builder().add("userId",userID+"")
                .add("objectId",id+"")
                .add("type",1+"")
                .build();
        Log.e("userid","============================================================================================================="+userID+"");
        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<DianzanBean>() {
            @Override
            public void onSuccess(DianzanBean response) {

                if (response.getResult() == 1){
                    ToastUtil.showShort(mContext,"点赞成功");
                }

            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(mContext,"点赞失败！");
            }
        });

    }

    public class ViewHolder {

        RoundImageView fuwu_img;
        ImageView fuwu_item_zan, fuwu_item_pl;
        TextView fuwu_item_title, fuwu_item_xiaotitle, fuwu_item_address, fuwu_item_zanTv,shouye_fuwu_data,shouye_fuwu_distance;
        RelativeLayout fuwu_rl;
        LinearLayout dianzanll,pinglunll,fenxiangll;

    }
}
