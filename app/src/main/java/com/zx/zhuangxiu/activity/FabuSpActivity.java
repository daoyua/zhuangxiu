package com.zx.zhuangxiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PermissionUtils;
import com.zx.zhuangxiu.ECApplication;
import com.zx.zhuangxiu.ImageYS;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.GridViewAddImgesAdpter;
import com.zx.zhuangxiu.adapter.SpinnerSpAdapter;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;
import com.zx.zhuangxiu.model.FabuSpBean;
import com.zx.zhuangxiu.model.ImageBean;
import com.zx.zhuangxiu.utils.ToastUtil;
import com.zx.zhuangxiu.view.MyGridView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FabuSpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText shangjifabu_gsname;
    private EditText shangjifabu_price;
    private EditText shangjifabu_jieshao;
    private MyGridView gw_lunbo;
    private MyGridView gw_detail;
    private MyGridView gw_sp_main;
    private List<String> datas_lunbo;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter_lunbo;
    private ArrayList datas_detail;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter_detail;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter_main;
    private ArrayList datas_main;
    private Spinner spinner;
    private SpinnerSpAdapter arr_adapter;
    private EditText sp_type;
    private EditText fabu_sp_name;
    private EditText fabu_sp_price;
    private EditText fabu_sp_detail_jieshao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu_sp);
        initView();
        getfenlei();
    }

    private int type_id=11;
    private List<DaxiaofenleiBean.DataBean> mlist = new ArrayList<>();

    private void getfenlei() {

        String url = URLS.getdaxiaolei(5);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DaxiaofenleiBean>() {


            @Override
            public void onSuccess(DaxiaofenleiBean response) {

                if (response.getResult() == 1) {
                    mlist.addAll(response.getData());
                    arr_adapter = new SpinnerSpAdapter(FabuSpActivity.this, mlist);

//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arr_adapter);
                    if (mlist.size() > 0) {
                        type_id = mlist.get(0).getId();
                    }

                    spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                            if (mlist.size() > 0) {
                                sp_type.setText(mlist.get(arg2).getCname());
                                type_id = mlist.get(arg2).getId();
                            }
                        }

                        public void onNothingSelected(AdapterView<?> arg0) {
                            sp_type.setText("");
                        }
                    });
                    //tablayout和viewpager关联
//                    mGoodsTab.setupWithViewPager(goodsViewPager);
//                    goodsViewpagerAdapter = new GoodsViewpagerAdapter(getSupportFragmentManager(), mlist);
//                    goodsViewPager.setAdapter(goodsViewpagerAdapter);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
    public void submit(String cdname , String broadcastUrl , String detailUrl , String picture ,int price ,int typeId ,String simple ) {
        String url = URLS.postSp();

        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("cdname ", cdname )

                .add("broadcastUrl ", broadcastUrl  )
                .add("detailUrl ", detailUrl )
                .add("picture  ", picture  )
                .add("price ", price  + "")
                .add("typeId ", typeId  + "")
                .add("simple ", simple  ).build();

        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<FabuSpBean>() {
            @Override
            public void onSuccess(FabuSpBean response) {
                if (response.getResult() == 1) {
//                    DingdanBean.DataBean data = response.getData();
//                   paylogId = data.getPaylogId();
//                    getjiesuan(payTYpe);
                } else {
                    ToastUtil.showShort(getApplicationContext(), "发布失败！！！");
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "请求失败！！！");
            }
        });
    }
    private void initView() {
        findViewById(R.id.fabu_sp_fanhui).setOnClickListener(this);
        findViewById(R.id.sp_fabu_tijao).setOnClickListener(this);
        fabu_sp_name = findViewById(R.id.fabu_sp_name);
        fabu_sp_price = findViewById(R.id.fabu_sp_price);
        fabu_sp_detail_jieshao = findViewById(R.id.fabu_sp_detail_jieshao);


        spinner = findViewById(R.id.spinner_sp_type);
        shangjifabu_gsname = findViewById(R.id.shangjifabu_gsname);

        sp_type = findViewById(R.id.sp_type);

        shangjifabu_price = findViewById(R.id.shangjifabu_gsname);
        shangjifabu_jieshao = findViewById(R.id.shangjifabu_gsname);

        gw_lunbo = findViewById(R.id.gw_lunbo);
        gw_detail = findViewById(R.id.gw_detail);
        gw_sp_main = findViewById(R.id.gw_sp_main);


        datas_lunbo = new ArrayList<>();
        gridViewAddImgesAdpter_lunbo = new GridViewAddImgesAdpter(datas_lunbo, this);
        gw_lunbo.setAdapter(gridViewAddImgesAdpter_lunbo);
        gw_lunbo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                current_type = 1;
                showDialogList();
            }
        });

        datas_detail = new ArrayList<>();
        gridViewAddImgesAdpter_detail = new GridViewAddImgesAdpter(datas_detail, this);
        gw_detail.setAdapter(gridViewAddImgesAdpter_detail);
        gw_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                current_type = 2;
                showDialogList();
            }
        });

        datas_main = new ArrayList<>();
        gridViewAddImgesAdpter_main = new GridViewAddImgesAdpter(datas_main, this);
        gw_sp_main.setAdapter(gridViewAddImgesAdpter_main);
        gw_sp_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                current_type = 3;
                showDialogList();
            }
        });


    }

    int current_type;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabu_sp_fanhui:
                finish();
                break;
                case R.id.sp_fabu_tijao:

                    Log.e("TAG", "type_id" + type_id );
                    String name = fabu_sp_name.getText().toString().trim();
                    if(TextUtils.isEmpty(name)){
                       Toast.makeText(FabuSpActivity.this,"商品名称不能为空",Toast.LENGTH_SHORT).show();
                       return;
                    }
                    String price = fabu_sp_price.getText().toString().trim();
                    if(TextUtils.isEmpty(price)){
                        Toast.makeText(FabuSpActivity.this,"价格不能为空",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int prices=0;
                    try{
                         prices=Integer.parseInt(price);
                    }catch (Exception e)
                    {
                        Toast.makeText(FabuSpActivity.this,"价格请输入数字",Toast.LENGTH_SHORT).show();
                        return;
                    }


                    String jieshao = fabu_sp_detail_jieshao.getText().toString().trim();//简介


                    StringBuffer stringBuffer_lunbo = new StringBuffer();
                    StringBuffer stringBuffer_detail = new StringBuffer();
                    StringBuffer stringBuffer_main = new StringBuffer();
                    if(datas_lunbo.size()==0){
                        Toast.makeText(FabuSpActivity.this,"轮播图不能为空",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i < datas_lunbo.size(); i++) {
                        stringBuffer_lunbo.append(datas_lunbo.get(i) + ",");
                    }
                    if(datas_detail.size()==0){
                        Toast.makeText(FabuSpActivity.this,"详情页不能为空",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i < datas_detail.size(); i++) {
                        stringBuffer_detail.append(datas_detail.get(i) + ",");
                    }
                    if(datas_detail.size()==0){
                        Toast.makeText(FabuSpActivity.this,"项目主图不能为空",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i < datas_main.size(); i++) {
                        stringBuffer_main.append(datas_main.get(i) + ",");
                    }
                submit(name,stringBuffer_lunbo.toString(),stringBuffer_detail.toString(),stringBuffer_main.toString(),prices,type_id,jieshao);
                break;
        }
    }

    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    private AlertDialog dialog;

    private void showDialogList() {
        LayoutInflater inflater = LayoutInflater.from(FabuSpActivity.this);
        View view = inflater.inflate(R.layout.my_info_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        TextView tv_paizhao = (TextView) view.findViewById(R.id.paizhao);
        TextView tv_xiangce = (TextView) view.findViewById(R.id.bendixiangce);
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
////                Toast.makeText(MyInfoActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
                    Toast.makeText(ECApplication.getApplication(), "请先打开相机权限", Toast.LENGTH_SHORT).show();
                    PermissionUtils.permission(Manifest.permission.CAMERA).request();
                    return;
                }
                Intent intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, IMAGE_RESULT_CODE);// 打开照相机
            }
        });
        tv_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MyInfoActivity.this, "本地相册", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK);// 打开照相机

            }
        });

        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {

                // 表示 调用照相机拍照
                case IMAGE_RESULT_CODE:
                    if (resultCode == RESULT_OK) {
                        Bundle bundle = data.getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");
//                    fabu_img.setImageBitmap(bitmap);
//                    photoPath(bitmap);
                        changebitmap(current_type, bitmap);
                    }
                    break;
                // 选择图片库的图片
                case PICK:
                    if (resultCode == RESULT_OK) {
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            Bitmap bit = ImageYS.getBitmapFormUri(FabuSpActivity.this, uri);
//                        photoPath(bit);
                            changebitmap(current_type, bit);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        }
    }

    //转化file文件
    public void changebitmap(int type, Bitmap bitmap) {
        BufferedOutputStream out = null;
        File file = new File(getCacheDir(), System.currentTimeMillis() + ".png");
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        upLoadImage(type, file);

    }

    /* 上传图片*/
    private void upLoadImage(final int type, File file) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在上传");
        progressDialog.show();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file));

        RequestBody requestBody = builder.build();
        OkHttpUtils.post(URLS.toimage(), requestBody, new OkHttpUtils.ResultCallback<ImageBean>() {
            @Override
            public void onSuccess(ImageBean imageBean) {
                if (imageBean.getResult() == 1) {
//                    imageurl = imageBean.getData().getUrl();
                    photoPath(type, imageBean.getData().getUrl());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
            }
        });
    }

    public void photoPath(int type, String path) {
        switch (type) {
            case 1://;unbo
                datas_lunbo.add(path);
                gridViewAddImgesAdpter_lunbo.notifyDataSetChanged();
                break;
            case 2://;unbo
                datas_detail.add(path);
                gridViewAddImgesAdpter_detail.notifyDataSetChanged();
                break;
            case 3://;unbo
                datas_main.add(path);
                gridViewAddImgesAdpter_main.notifyDataSetChanged();
                break;
        }


    }
}
