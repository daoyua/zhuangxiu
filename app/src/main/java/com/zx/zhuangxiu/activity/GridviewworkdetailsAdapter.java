package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;

import java.util.List;

public class GridviewworkdetailsAdapter extends BaseAdapter {

    private Context context;
    private List<String> mlist ;

    public GridviewworkdetailsAdapter(Context context, List<String> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.workdetailsgridview_item, null);
            holder.images = (ImageView) convertView.findViewById(R.id.gridviewworkdetails_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(URLS.HTTP+mlist.get(position)).error(R.mipmap.logo_zhanwei).fit().into(holder.images);
        return convertView;
    }



    public class ViewHolder {

        ImageView images;

    }
}
