package com.zx.zhuangxiu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GridViewAddImgesAdpter extends BaseAdapter {
    private List<String> datas;
    private Context context;
    private LayoutInflater inflater;
    /**
     * 可以动态设置最多上传几张，之后就不显示+号了，用户也无法上传了
     * 默认9张
     */
    private int maxImages =6;

    public GridViewAddImgesAdpter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 获取最大上传张数
     *
     * @return
     */
    public int getMaxImages() {
        return maxImages;
    }

    /**
     * 设置最大上传张数
     *
     * @param maxImages
     */
    public void setMaxImages(int maxImages) {
        this.maxImages = maxImages;
    }

    /**
     * 让GridView中的数据数目加1最后一个显示+号
     *
     * @return 返回GridView中的数量
     */
    @Override
    public int getCount() {
        int count = datas == null ? 1 : datas.size()+1 ;
        if (count > maxImages) {
            return datas.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void notifyDataSetChanged(List<String> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (datas != null && position <datas.size()) {
         /*   final File file = new File(.toString());
            Picasso.with(context)
                    .load(file)
                    .fit()
                    .into(viewHolder.ivimage);*/
//            viewHolder.ivimage.setImageBitmap((Bitmap) datas.get(position));
            Picasso.with(context)
                    .load(URLS.HTTP+datas.get(position))
                    .fit()
                    .into(viewHolder.ivimage);
            viewHolder.btdel.setVisibility(View.VISIBLE);
            viewHolder.btdel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else {

            Picasso.with(context)
                    .load(R.mipmap.image_add)
                    .fit()
                    .into(viewHolder.ivimage);
            viewHolder.ivimage.setScaleType(ImageView.ScaleType.FIT_XY);
            viewHolder.btdel.setVisibility(View.GONE);
        }

        return convertView;

    }

    public class ViewHolder {
        public final ImageView ivimage;
        public final Button btdel;
        public final View root;

        public ViewHolder(View root) {
            ivimage = (ImageView) root.findViewById(R.id.iv_image);
            btdel = (Button) root.findViewById(R.id.bt_del);
            this.root = root;
        }
    }
}
