package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.MyOrderBean;

import java.util.List;

public class MyzhunbeisxAdapter extends BaseAdapter {

    private Context context;
    private List<MyOrderBean.DataBean> mlist;

    public MyzhunbeisxAdapter(Context context, List<MyOrderBean.DataBean> mlist) {
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
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.zhunbeishixiangadapter_item, null);
            holder.myimage = view.findViewById(R.id.zhunbeiitem_image);//图片
            holder.mingcheng = view.findViewById(R.id.zhunbeiitem_mingcheng);//名称
            holder.mydizhi = view.findViewById(R.id.zhunbeiitem_address);//地址
            holder.mynigong = view.findViewById(R.id.nigong);
            holder.submit = view.findViewById(R.id.submit);
            view.setTag(holder);
        } else {
            if (mlist.size() != 0) {
                holder = (ViewHolder) view.getTag();
                holder.mingcheng.setText(mlist.get(i).getItemlist().get(0).getCdname());
                holder.mydizhi.setText(mlist.get(i).getItemlist().get(0).getBuyprice() + "");
                holder.mynigong.setText(mlist.get(i).getItemlist().get(0).getCount() + "");
                Picasso.with(context).load(URLS.HTTP + mlist.get(i).getItemlist().get(0).getPicUrl()).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(holder.myimage);
                if (mlist.get(i).getOrderstatus() == 3) {

                    holder.submit.setText("确认收货");
                    holder.submit.setVisibility(View.VISIBLE);
                    holder.submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            A(mlist.get(i).getId(), i);
                        }
                    });
                } else {
                    holder.submit.setVisibility(View.GONE);
                }

            }
        }
        return view;
    }

    public void A(int oederID, final int position) {
        OkHttpUtils.get(URLS.HTTP + "/api/order/conformOrder?orderId=" + oederID, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                if (response.getResult() == 1) {
                    Toast.makeText(context, "确认收货成功", Toast.LENGTH_SHORT).show();
                    mlist.get(position).setOrderstatus(4);
                    mlist.remove(position);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    public class ViewHolder {
        ImageView myimage;
        TextView mingcheng, mydizhi, submit, mynigong;


    }
}
