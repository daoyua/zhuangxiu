package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.MyqiyedongtaiBean;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.List;

public class MyDongTaiQYListAdapter extends BaseAdapter {
    private List<MyqiyedongtaiBean.DataBean> list;
    private Context context;

    public MyDongTaiQYListAdapter(List<MyqiyedongtaiBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.fuwu_fragment_item, null);
            holder.fuwu_item_title = (TextView) view.findViewById(R.id.fuwu_title);
            holder.fuwu_item_xiaotitle = (TextView) view.findViewById(R.id.fuwu_xiaotitle);
            holder.fuwu_item_address = (TextView) view.findViewById(R.id.fuwu_item_address);
            holder.fuwu_item_zanTv = (TextView) view.findViewById(R.id.fuwu_item_zanTv);
            holder.fuwu_img = (RoundImageView) view.findViewById(R.id.fuwu_img);
            holder.fuwu_item_zan = (ImageView) view.findViewById(R.id.fuwu_item_zan);
            holder.fuwu_item_pl = (ImageView) view.findViewById(R.id.fuwu_item_pl);
            holder.fuwu_rl = (RelativeLayout) view.findViewById(R.id.fuwu_rl);
            holder.dianzanll = (LinearLayout) view.findViewById(R.id.dianzanll);
            holder.pinglunll = (LinearLayout) view.findViewById(R.id.pinglunll);
            holder.fenxiangll = (LinearLayout) view.findViewById(R.id.fenxiangll);
            holder.line = (LinearLayout) view.findViewById(R.id.line);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.fuwu_item_title.setText(list.get(i).getName());//标题
        holder.fuwu_item_address.setText(list.get(i).getAddress());//工作地址
//        holder.fuwu_item_zanTv.setText(list.get(i).getThumbsUp());//点赞数量
       /* if (list.get(i).getStatus().equals("1")){
            holder.zhuangtai.setText("招聘状态：已发布");//发布状态
        }else if (list.get(i).getStatus().equals("2")){
            holder.zhuangtai.setText("招聘状态：未发布");//发布状态
        }*/
       holder.line.setVisibility(View.GONE);

        holder.fuwu_item_xiaotitle.setText("详细内容:" + list.get(i).getRequires());//详细内容
        String imgUrl = list.get(i).getImgUrl();

        if (!TextUtils.isEmpty(imgUrl)) {
            String[] split = imgUrl.split(",");
            if (!split[0].startsWith("http://") && !split[0].startsWith("https://")) {
                split[0] = URLS.HTTP+split[0];
            }
            Log.e("TAG",split[0]);
            Picasso.with(context).load(split[0]).error(R.mipmap.logo_zhanwei)
                    .placeholder(R.mipmap.logo_zhanwei).fit().into(holder.fuwu_img);
        }


        return view;
    }

    public class ViewHolder {
        RoundImageView fuwu_img;
        ImageView fuwu_item_zan, fuwu_item_pl;
        TextView fuwu_item_title, fuwu_item_xiaotitle, fuwu_item_address, fuwu_item_zanTv;
        RelativeLayout fuwu_rl;
        LinearLayout dianzanll, pinglunll, fenxiangll,line;
    }
}
