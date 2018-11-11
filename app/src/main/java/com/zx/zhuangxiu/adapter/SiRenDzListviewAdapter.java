package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.BestService;

import java.util.List;

public class SiRenDzListviewAdapter extends BaseAdapter {


    private Context mContext;
    private List<BestService.DataBean> mList;

    public SiRenDzListviewAdapter(Context mContext, List<BestService.DataBean> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.jinpai_item, null);
            holder.jinpai_item_details = (TextView) convertView.findViewById(R.id.jinpai_item_details);
            holder.jinpai_item_img = (ImageView) convertView.findViewById(R.id.jinpai_item_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (mList.size() != 0) {
            String imgUrl = mList.get(position).getImgUrl();
            if (!TextUtils.isEmpty(imgUrl) && imgUrl.contains(",")) {
                String[] split = imgUrl.split(",");
                if (!split[0].startsWith("http://") && !split[0].startsWith("https://")) {
                    split[0] = URLS.HTTP + split[0];
                }
                Picasso.with(mContext).load(split[0]).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(holder.jinpai_item_img);

            }

        }

     /*   holder.jinpai_item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoundFuWuDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        holder.jinpai_item_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoundFuWuDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mList.get(position).getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });*/

        return convertView;
    }

    public class ViewHolder {

        ImageView jinpai_item_img;
        TextView jinpai_item_details;

    }

}
