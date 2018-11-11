package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.DeleteaddressBean;
import com.zx.zhuangxiu.model.Guo;
import com.zx.zhuangxiu.model.LookaddressBean;
import com.zx.zhuangxiu.model.XiuGaiAddress;
import com.zx.zhuangxiu.utils.LocalJsonResolutionUtils;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity implements OnClickListener{

    private EditText add_address_name, add_address_mobile,add_address_address;
    private TextView add_address_save, add_address_back;
    private CheckBox add_address_morendizhi;

    //地址选择开始
    private Spinner ssheng,sshi,sxian;
    private List<String> listsheng=new ArrayList<>();
    private List<String> listshi=new ArrayList<>();
    private List<String> listxian=new ArrayList<>();
    private ArrayAdapter<String> shengadapter;
    private ArrayAdapter<String> shiadapter;
    private ArrayAdapter<String> xianadapter;

    private Map<String,List<String>> map= new HashMap<>();
    private Map<String,List<String>>mapshi= new HashMap<>();

    private String stringsheng,stringshi,stringxian;
    private EditText nameeditext;
    private EditText mobiletext;
    private EditText xiangxiaddress;
    private String name;
    private String dianhua;
    private String xiangxidizhi;
    private int userid;
    private String isTrue;
    private LookaddressBean.DataBean data;
    private TextView mDelete;
    //地址选择结束

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        data = (LookaddressBean.DataBean) getIntent().getSerializableExtra("data");

        initView();
    }

    private void initView() {

        ssheng=(Spinner)findViewById(R.id.ssheng);//省
        sshi=(Spinner)findViewById(R.id.sshi);//市
        sxian=(Spinner)findViewById(R.id.sxian);//县

        add_address_save = (TextView)findViewById(R.id.add_address_save);
        add_address_back = (TextView)findViewById(R.id.add_address_back);
        nameeditext = findViewById(R.id.add_address_name);//姓名
        mobiletext = findViewById(R.id.add_address_mobile);//电话
        xiangxiaddress = findViewById(R.id.add_address_address);//详细地址
        add_address_back.setOnClickListener(this);
        add_address_save.setOnClickListener(this);
        add_address_morendizhi = (CheckBox)findViewById(R.id.add_address_morendizhi);
        add_address_name = (EditText)findViewById(R.id.add_address_name);
        add_address_mobile = (EditText)findViewById(R.id.add_address_mobile);
        add_address_address = (EditText)findViewById(R.id.add_address_address);
        mDelete = findViewById(R.id.delete);
        liandong();
      if (data!=null){
          mDelete.setVisibility(View.VISIBLE);
          stringsheng =data.getProvince();
          stringshi=data.getCity();
          stringxian=data.getCounty();
          add_address_name.setText(data.getName());
          add_address_mobile.setText(data.getTele());
          add_address_address.setText(data.getDetaladdress());
          if (data.getStatus()==1){
              add_address_morendizhi.setChecked(true);
          }else {
              add_address_morendizhi.setChecked(false);
          }

      }
      mDelete.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              String url = URLS.shanchudizhi(data.getId(), URLS.getUser_id());
              OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DeleteaddressBean>() {
                  @Override
                  public void onSuccess(DeleteaddressBean response) {
                      if (response.getResult() == 1){
                          Toast.makeText(AddAddressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                          Intent intent=new Intent(AddAddressActivity.this,MylookaddressActivity.class);
                          startActivity(intent);
                      }
                  }
                  @Override
                  public void onFailure(Exception e) {

                  }
              });
          }
      });

    }


    private void getInfo() {
        name = nameeditext.getText().toString().trim();
        dianhua = mobiletext.getText().toString().trim();
        xiangxidizhi = xiangxiaddress.getText().toString().trim();
        userid = URLS.getUser_id();
        isTrue = "0";
       if(add_address_morendizhi.isChecked()){
           isTrue ="1";
       }else {
           isTrue = "0";
       }
        if (!name.equals("")&&!dianhua.equals("")&&!xiangxidizhi.equals("")&&!stringsheng.equals("")&&!stringshi.equals("")&&!stringxian.equals("")){
            String url = URLS.myXiuGaiAddressShow(name,dianhua,stringsheng,stringshi,stringxian,xiangxidizhi,isTrue,userid);
//        Log.d("xxx", "url==========="+url);
            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<XiuGaiAddress>() {
                @Override
                public void onSuccess(XiuGaiAddress response) {
                    if(response.getResult()== 1){
                        Toast.makeText(AddAddressActivity.this, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AddAddressActivity.this,MylookaddressActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(AddAddressActivity.this, ""+response.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    ToastUtil.showShort(getApplicationContext(),"保存失败！");
}
            });
                    }else {
                    ToastUtil.showShort(getApplicationContext(),"请填写完整信息！");
                    }
                    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_address_back:
                setResult(RESULT_OK);
                this.finish();
                break;
            case R.id.add_address_save:
                getInfo();
                break;
        }
    }

    //选择地址开始
    public void liandong() {
        //得到本地json文本内容
        String fileName = "province.json";
        String foodJson = LocalJsonResolutionUtils.getJson(AddAddressActivity.this, fileName);
        //转换为对象
        Guo guo = LocalJsonResolutionUtils.JsonToObject(foodJson, Guo.class);
        for (int i=0;i<guo.getList().size();i++){
            listsheng.add(guo.getList().get(i).getName());//将省份添加到集合
            List<String> lists=new ArrayList<>();
            for (int a=0;a<guo.getList().get(i).getCity().size();a++){//的到市的集合长度
                lists.add(guo.getList().get(i).getCity().get(a).getName());//将市添加到集合
                List<String>listx=new ArrayList<>();
                for (int b=0;b<guo.getList().get(i).getCity().get(a).getArea().size();b++){
                    listx.add(guo.getList().get(i).getCity().get(a).getArea().get(b));//得到县的集合，
                }
                mapshi.put(guo.getList().get(i).getCity().get(a).getName(),listx); //然后将市和县连城map；
            }
            map.put(guo.getList().get(i).getName(),lists);//将省和市弄成map。《省，市集合》

        }

        spinerit();


    }
    public void  spinerit(){
        shengadapter= new ArrayAdapter<String>(AddAddressActivity.this,R.layout.spinner_item,R.id.tvSpinner,listsheng);//设置省的适配器
        shengadapter.setDropDownViewResource(R.layout.spinner_down_item);//设置省的下拉样式
        ssheng.setAdapter(shengadapter);//设置适配器
        ssheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringsheng=ssheng.getSelectedItem().toString();
                listshi=map.get(stringsheng);
                shiadapter= new ArrayAdapter<String>(AddAddressActivity.this,R.layout.spinner_item,R.id.tvSpinner,listshi);//设置市的适配器
                shiadapter.setDropDownViewResource(R.layout.spinner_down_item);//设置市的下拉样式
                sshi.setAdapter(shiadapter);//设置适配器
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sshi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringshi=sshi.getSelectedItem().toString();
                listxian=mapshi.get(stringshi);
                xianadapter= new ArrayAdapter<String>(AddAddressActivity.this,R.layout.spinner_item,R.id.tvSpinner,listxian);//设置县的适配器
                xianadapter.setDropDownViewResource(R.layout.spinner_down_item);//设置县的下拉样式
                sxian.setAdapter(xianadapter);//设置适配器
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sxian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringxian=sxian.getSelectedItem().toString();
                Log.d("徐康",stringsheng+"===="+stringshi+"===="+stringxian);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
