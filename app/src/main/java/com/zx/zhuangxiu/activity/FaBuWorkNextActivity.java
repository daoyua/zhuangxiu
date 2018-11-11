package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.PersonGridViewAdapter;
import com.zx.zhuangxiu.model.AddGongZhong;

import java.util.List;
/**
 * 首页-找工作-发布-下一步
 * */
public class FaBuWorkNextActivity extends AppCompatActivity implements View.OnClickListener{

    private GridView fabu_next_gv1, fabu_next_gv2;
    private TextView fabu_next_back;
    private TextView fabu_next_fabu;
    private PersonGridViewAdapter personGridViewAdapter;
    private List<String> mList;
    String name[]={"结晶工","安装工","小工","包工","搬运工"};
    String name2[]={"钻孔","打墙","保洁","拆房","电焊", "管道", "防水", "搬运", "开槽", "其他"};


    private EditText fabu_next_gongzhong, fabu_next_beizhu,fabu_next_num;
    private Button fabu_next_tijiaoGz;
    private String mingcheng = "", address="", mianji = "", fabuTime = "", wanGongTime = "",zhiwei = "", tiaojian = "";
    private String imgId;

    private int data = 0;
    private int fuWuId = 0;
    private int changPinId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_work_next);

        Bundle bundle = getIntent().getExtras();
        imgId = bundle.getString("imgId");
        mingcheng = bundle.getString("name");
        address = bundle.getString("address");
        mianji = bundle.getString("mianji");
        fabuTime = bundle.getString("kaigong");
        wanGongTime = bundle.getString("wanhong");
        zhiwei = bundle.getString("zhiwei");
        tiaojian = bundle.getString("tiaojian");

        Log.d("ddd", "发布工作Next接收数据-----imgId------"+imgId+"------mingcheng-------"+mingcheng+"-------address-----"+
                address+"-------mianji------"+mianji+"-------fabuTime-----"+fabuTime+"------wanGongTime------"+wanGongTime);

        initView();
    }

    private void initView() {
        fabu_next_back = (TextView) findViewById(R.id.fabu_next_back);
        fabu_next_fabu = (TextView) findViewById(R.id.fabu_next_fabu);
        fabu_next_num = (EditText)findViewById(R.id.fabu_next_num);

        fabu_next_fabu.setOnClickListener(this);
        fabu_next_back.setOnClickListener(this);

        fabu_next_gv1 = (GridView) findViewById(R.id.fabu_next_gv1);
        fabu_next_gv2 = (GridView) findViewById(R.id.fabu_next_gv2);

        fabu_next_gongzhong = (EditText)findViewById(R.id.fabu_next_gongzhong);
        fabu_next_beizhu = (EditText)findViewById(R.id.fabu_next_beizhu);
        fabu_next_tijiaoGz = (Button) findViewById(R.id.fabu_next_tijiaoGz);

        fabu_next_tijiaoGz.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabu_next_back:
                this.finish();
                break;
            case R.id.fabu_next_tijiaoGz:
                addGongZhongInfo();
                break;
            case R.id.fabu_next_fabu:
                if(data == 0){
                    Toast.makeText(FaBuWorkNextActivity.this, "请先提交工种！", Toast.LENGTH_SHORT).show();
                }else if(fabu_next_beizhu.getText().toString().equals("")){
                    Toast.makeText(FaBuWorkNextActivity.this, "备注信息不能为空！", Toast.LENGTH_SHORT).show();
                }else if(fabu_next_num.getText().toString().equals("")){
                    Toast.makeText(FaBuWorkNextActivity.this, "需要人数不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                    getFaBuOneInfo();
                }
                break;
        }
    }

    private void getFaBuOneInfo() {
     /*   String url = URLS.SaveJob(URLS.getUser_id(),mingcheng,"","",address,mianji,zhiwei,tiaojian,imgId,fabuTime,wanGongTime,fabu_next_gongzhong.getText().toString());

//        String url = URLS.faBuOneShow(zhiwei, tiaojian,mingcheng, address, mianji, imgId,data, fabu_next_beizhu.getText().toString(), fabuTime, wanGongTime, "2",URLS.getUser_id());
        Log.d("ddd","FaBuWorkNextActivity==fuWuId===url------"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                int result = response.getResult();
//               Log.d("ddd","FaBuWorkNextActivity=====fuWuId------"+fuWuId+"-----data===="+data);
                if(fuWuId == 0){
                    Toast.makeText(FaBuWorkNextActivity.this, "发布工作失败！", Toast.LENGTH_SHORT).show();
                }else {
                    getFaBuTwoInfo(fuWuId);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });*/
    }

    private void getFaBuTwoInfo(int fuWuId) {
        String url = URLS.faBuTwoShow(data, fuWuId,fabu_next_num.getText().toString());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddGongZhong>() {
            @Override
            public void onSuccess(AddGongZhong response) {
                if(response.getId() == 0){
                    changPinId = response.getData().get(0);
                    Toast.makeText(FaBuWorkNextActivity.this, "发布工作成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FaBuWorkNextActivity.this, FoundWorkActivity.class);
                    startActivity(intent);
                    FaBuWorkNextActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void addGongZhongInfo() {
        String url = URLS.addGongZhongShow(fabu_next_gongzhong.getText().toString(), URLS.getUser_id());
//        Log.d("ddd","提交工种=====url------"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddGongZhong>() {
            @Override
            public void onSuccess(AddGongZhong response) {
                    if(response.getId() == 0){
                        data = response.getData().get(0);
                        Toast.makeText(FaBuWorkNextActivity.this, "提交工种成功！", Toast.LENGTH_SHORT).show();
                        fabu_next_fabu.setVisibility(View.VISIBLE);

                    }else {
                        Toast.makeText(FaBuWorkNextActivity.this, "提交工种失败！", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
