package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.MyDingDanGRXQFuWuGongZhong;
import com.zx.zhuangxiu.model.MyDingDanGRXQFuWuXingQing;
import com.zx.zhuangxiu.utils.ToTime;

/**
 *次页面为订单详情页面
 */
public class MyDingDanGRFuWuActivity extends AppCompatActivity {
    private String imageurl;
    private int recordId;
    private ImageView image;
    private TextView back;
    private TextView name, dizhi, dianzan, xiangmumingcheng, gongzhong, beizhu,kaishijian,jieshijian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dan_grfu_wu);
//        Bundle bundle = getIntent().getExtras();
//        imageurl = bundle.getString("imageurl");
//        recordId = bundle.getInt("recordId");
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        dizhi = (TextView) findViewById(R.id.dizhi);
        xiangmumingcheng = (TextView) findViewById(R.id.xiangmumingcheng);
        gongzhong = (TextView) findViewById(R.id.gongzhong);
        beizhu = (TextView) findViewById(R.id.beizhu);
        kaishijian = (TextView) findViewById(R.id.kaishijian);
        jieshijian = (TextView) findViewById(R.id.jieshushijian);
        back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Picasso.with(MyDingDanGRFuWuActivity.this).load(imageurl).error(R.mipmap.logo_zhanwei).into(image);

        into();
    }

    private void into() {
        String url = URLS.mydingdangerenxiangqingfuwugongzhong(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyDingDanGRXQFuWuGongZhong>() {
            @Override
            public void onSuccess(MyDingDanGRXQFuWuGongZhong response) {
                if (response.getId() == 0) {
                    String gz = response.getData().get(0).getJskIndexService().get(0).getJskIndexWork().get(0).getWorkName();
                    gongzhong.setText(gz);

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        String urll = URLS.mydingdangerenxiangqingfuwuxinxi(URLS.getUser_id());
        OkHttpUtils.get(urll, new OkHttpUtils.ResultCallback<MyDingDanGRXQFuWuXingQing>() {
            @Override
            public void onSuccess(MyDingDanGRXQFuWuXingQing response) {
                if (response.getId() == 0) {
                    beizhu.setText(response.getData().get(0).getJskIndexService().get(0).getRemark());
                    xiangmumingcheng.setText(response.getData().get(0).getJskIndexService().get(0).getJskSysUser().get(0).getUserName());
                    dianzan.setText(""+response.getData().get(0).getJskIndexService().get(0).getPraiseNum());
                    dizhi.setText(response.getData().get(0).getJskIndexService().get(0).getAddress());
                    name.setText(response.getData().get(0).getJskIndexService().get(0).getTitle());
                    kaishijian.setText("项目开始时间："+ToTime.getDateTimeFromMillisecond(response.getData().get(0).getJskIndexService().get(0).getStartDate()));
                    jieshijian.setText("项目结束时间："+ToTime.getDateTimeFromMillisecond(response.getData().get(0).getJskIndexService().get(0).getEndDate()));

                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
