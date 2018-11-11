package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.SySearchTwo;
import com.zx.zhuangxiu.model.ZgrSearchOne;
import com.zx.zhuangxiu.model.ZgrSearchTwo;
import com.zx.zhuangxiu.view.CircleImageView;

import java.util.List;

public class ZgrSearchAdapter extends BaseAdapter{

    private Context mContext;
    private List<ZgrSearchOne.DataBean> mList;

    public ZgrSearchAdapter(Context mContext, List<ZgrSearchOne.DataBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.zgr_search_item, null);
            holder.zgr_search_title = (TextView) convertView.findViewById(R.id.zgr_search_title);
            holder.zgr_search_xiaotitle = (TextView) convertView.findViewById(R.id.zgr_search_xiaotitle);
            holder.zgr_search_leixing = (TextView) convertView.findViewById(R.id.zgr_search_leixing);
            holder.zgr_search_img = (CircleImageView) convertView.findViewById(R.id.zgr_search_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        ZgrSearchOne.DataBean collectList = mList.get(position);
            holder.zgr_search_title.setText("名称: "+collectList.getRealname());
            holder.zgr_search_xiaotitle.setText("个人空间: "+collectList.getSpace());
          /*  if(mList.get(position).getJskIndexWork().size() == 0){
                holder.zgr_search_leixing.setText("从事工种: ");
            }else {
                holder.zgr_search_leixing.setText("从事工种: "+collectList.getJskIndexWork().get(0).getWorkName());
            }*/
        String imgUrl = collectList.getUserUrl();
        if (!imgUrl.startsWith("http://")&&!imgUrl.startsWith("https://")){
            imgUrl=URLS.HTTP;
        }

        Picasso.with(mContext).load(imgUrl).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.zgr_search_img);



        return convertView;
    }

    public class ViewHolder {

        CircleImageView zgr_search_img;
        TextView zgr_search_title, zgr_search_xiaotitle, zgr_search_leixing;

    }

}
