package com.zx.zhuangxiu.activity.automap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.zx.zhuangxiu.R;

import java.util.ArrayList;

public class AutoMapListAdapter extends BaseAdapter {
    ArrayList<PoiItem> pois;
    private Context mContext;

    public AutoMapListAdapter(Context mContext, ArrayList<PoiItem> poiss) {

        this.mContext = mContext;
        pois=poiss;

    }
  public void   setData(ArrayList<PoiItem> poiss){
      pois=poiss;
      notifyDataSetChanged();
  };
    @Override
    public int getCount() {
        return pois.size();
    }

    @Override
    public PoiItem getItem(int position) {
        return pois.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.auto_select_list_item, null);
            holder.itemt_auto_address = (TextView) convertView.findViewById(R.id.itemt_auto_address);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (pois.size() != 0) {
            PoiItem poiItem = pois.get(position);
            holder.itemt_auto_address.setText(poiItem.getTitle()+poiItem.getSnippet());
        }
        return convertView;
    }
    public class ViewHolder {

        TextView itemt_auto_address;

    }
}
