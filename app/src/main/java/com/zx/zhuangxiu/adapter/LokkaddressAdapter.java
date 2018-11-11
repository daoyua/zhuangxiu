package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.AddAddressActivity;
import com.zx.zhuangxiu.model.DeleteaddressBean;
import com.zx.zhuangxiu.model.LookaddressBean;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class LokkaddressAdapter extends BaseAdapter{


    private Context context;
    private List<LookaddressBean.DataBean> mlist = new ArrayList<>();

    public LokkaddressAdapter(Context context, List<LookaddressBean.DataBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.lookaddressitem,null);
            holder.nametext = view.findViewById(R.id.lookitem_name);
            holder.phonetext = view.findViewById(R.id.lookitem_phone);
            holder.dizhitext = view.findViewById(R.id.lookitem_dizhi);
            holder.shanchulayout = view.findViewById(R.id.lookitem_shanchu);
            holder.bianjilayout = view.findViewById(R.id.lookitem_bianji);
            holder.tv_default = view.findViewById(R.id.tv_defaultw);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        if (mlist.size() != 0){
            holder.nametext.setText(mlist.get(i).getName());
            holder.phonetext.setText(mlist.get(i).getTele());
            holder.dizhitext.setText(mlist.get(i).getDetaladdress());
            if (mlist.get(i).getStatus()==0){
                holder.tv_default.setVisibility(View.GONE);
            }else {
                holder.tv_default.setVisibility(View.VISIBLE);
            }
        }

        holder.shanchulayout.setTag(i);
        holder.bianjilayout.setTag(i);
        //添加删除点击事件
        holder.shanchulayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = mlist.get(i).getId();
                String url = URLS.shanchudizhi(id, URLS.getUser_id());
                OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DeleteaddressBean>() {
                    @Override
                    public void onSuccess(DeleteaddressBean response) {
                        if (response.getResult() == 1){
//                            ToastUtil.showShort(context,deletelist.get(i).getMsg());
                            mlist.remove(i);
                            notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.showShort(context,"删除失败！");
                    }
                });
                ToastUtil.showShort(context,"点击了删除！");
            }
        });



        //添加编辑点击事件
        holder.bianjilayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddAddressActivity.class);
                intent.putExtra("data",mlist.get(i));
                context.startActivity(intent);


            }
        });

        return view;
    }
    public class ViewHolder {

        TextView nametext,phonetext,dizhitext,tv_default;
        LinearLayout shanchulayout,bianjilayout;

    }

}
