package com.zx.zhuangxiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PermissionUtils;
import com.zx.zhuangxiu.ECApplication;
import com.zx.zhuangxiu.ImageYS;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.ChengGongBean;
import com.zx.zhuangxiu.model.ImageBean;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 商机加盟
 */
public class FabushangjiActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText gongsiname;
    private EditText gerenname;
    private EditText hezuoyaoqiu;
    private EditText hezuoshuliang;
    private EditText xiangxijieshao;
    private EditText youxiaoqi;
    private ImageView xiangmutupian;
    private ImageView yingyezheng;
    private ImageView yingyezhaopian;
    private TextView renzhengfeitext;
    private TextView tijiaotext;
    private TextView fanhuitext;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    private AlertDialog dialog;
    private int imagetap;//判断是那个控件点击了
    private String imageurl;
    private String XMURL;
    private String YEURL;
    private String YYZURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabushangji);

        intitview();
        tijiaotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettijiao();
            }
        });

        fanhuitext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void intitview() {
        gongsiname = findViewById(R.id.shangjifabu_gsname);//公司名称
        gerenname = findViewById(R.id.shangjifabu_gerenname);//个人姓名
        hezuoyaoqiu = findViewById(R.id.shangjifabu_hzyq);//合作要求
        xiangxijieshao = findViewById(R.id.shangjifabu_xiangxijs);//详细介绍
        youxiaoqi = findViewById(R.id.shangjifabu_youoxiaoqi);//有效期限
        hezuoshuliang = findViewById(R.id.shangjifabu_hezuoshuliang);//合作电话
        xiangmutupian = findViewById(R.id.shangjifabu_img1);//项目图片
        yingyezheng = findViewById(R.id.shangjifabu_img2);//营业执照
        yingyezhaopian = findViewById(R.id.shangjifabu_img3);//营业图片
        renzhengfeitext = findViewById(R.id.shangjifabu_renzhengfei);
        tijiaotext = findViewById(R.id.shangjifabu_tijao);
        fanhuitext = findViewById(R.id.shangjifabu_fanhui);

        xiangmutupian.setOnClickListener(this);
        yingyezheng.setOnClickListener(this);
        yingyezhaopian.setOnClickListener(this);
        fanhuitext.setOnClickListener(this);
        youxiaoqi.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        youxiaoqi.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shangjifabu_fanhui:
                this.finish();
                break;
            case R.id.shangjifabu_img1://项目图片
                imagetap = 0;
                showDialogList();
                break;
            case R.id.shangjifabu_img2://营业执照
                imagetap = 1;
                showDialogList();
                break;
            case R.id.shangjifabu_img3://营业图片
                imagetap = 2;
                showDialogList();
                break;


        }

    }


    //打开相机或图库
    @SuppressLint("WrongConstant")
    private void showDialogList() {

        LayoutInflater inflater = LayoutInflater.from(FabushangjiActivity.this);
        View view = inflater.inflate(R.layout.my_info_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        TextView tv_paizhao = (TextView) view.findViewById(R.id.paizhao);
        TextView tv_xiangce = (TextView) view.findViewById(R.id.bendixiangce);
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                Toast.makeText(MyInfoActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                if (!PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
                    Toast.makeText(ECApplication.getApplication(), "请先打开相机权限", Toast.LENGTH_SHORT).show();
                    PermissionUtils.permission(Manifest.permission.CAMERA).request();
                    return;
                }
                dialog.dismiss();
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

    private void gettijiao() {

        String mgongsiname = gongsiname.getText().toString().trim();
        String mgerenname = gerenname.getText().toString().trim();
        String mhezuoyaoqiu = hezuoyaoqiu.getText().toString().trim();
        String mxiangxijieshao = xiangxijieshao.getText().toString().trim();
        String myouxiaoqi = youxiaoqi.getText().toString().trim();
        String mhezuoshuliang = hezuoshuliang.getText().toString().trim();
        String mrenzhnegfei = renzhengfeitext.getText().toString().trim();
        if (TextUtils.isEmpty(mgongsiname)) {
            ToastUtil.showShort(getApplicationContext(), "公司名称没填");
            return;
        }
        if (TextUtils.isEmpty(mgerenname)) {
            ToastUtil.showShort(getApplicationContext(), "你的姓名没填");
            return;
        }
        if (TextUtils.isEmpty(mhezuoyaoqiu)) {
            ToastUtil.showShort(getApplicationContext(), "合作要求没填");
            return;
        }
        if (TextUtils.isEmpty(myouxiaoqi)) {
            ToastUtil.showShort(getApplicationContext(), "有限期限没填");
            return;
        }
        if (TextUtils.isEmpty(mhezuoshuliang)) {
            ToastUtil.showShort(getApplicationContext(), "联系电话没填");
            return;
        }
//
//        if (!mgongsiname.equals("") && !mgerenname.equals("") && !mhezuoyaoqiu.equals("") && !mxiangxijieshao.equals("")
//                && !myouxiaoqi.equals("") && !mhezuoshuliang.equals("") && RegexUtils.isMobileExact(mhezuoshuliang) && !mrenzhnegfei.equals("")) {

            String url = URLS.getshangjifabu(mgongsiname, mgerenname, mhezuoyaoqiu, mxiangxijieshao, myouxiaoqi, mhezuoshuliang,
                    XMURL, "", YEURL, mrenzhnegfei, URLS.getUser_id());

            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChengGongBean>() {
                @Override
                public void onSuccess(ChengGongBean response) {

                    if (response.getResult() == 1) {
                        ToastUtil.showShort(getApplicationContext(), "上传成功");
                        finish();
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    ToastUtil.showShort(getApplicationContext(), "上传失败");
                }
            });
        }
//        else {
//            ToastUtil.showShort(getApplicationContext(), "请输入完整的信息");
//        }
//    }

    //转化file文件
    public void changebitmap(Bitmap bitmap) {
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


        switch (imagetap) {
            case 0://项目图片
                upLoadImage(file);

                break;
            case 1://营业执照
                upLoadImage(file);

                break;
            case 2://营业图片
                upLoadImage(file);

                break;

        }

    }

    /* 上传图片*/
    private void upLoadImage(File file) {
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
                    progressDialog.dismiss();
                    imageurl = imageBean.getData().getUrl();
                    switch (imagetap) {
                        case 0://项目照片
                            XMURL = imageurl;
                            break;
                        case 1://营业执照
                            YEURL = imageurl;
                            break;
                        case 2://营业照片
                            YYZURL = imageurl;
                            break;

                    }
                    imageurl = "";
                }
            }

            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
            }
        });
    }

    //设置开工时间
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(FabushangjiActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int a = monthOfYear + 1;
                youxiaoqi.setText(year + "-" + a + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    v.clearFocus();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    //系统回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case IMAGE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    switch (imagetap) {
                        case 0://项目图片
                            xiangmutupian.setImageBitmap(bitmap);
                            changebitmap(bitmap);
                            break;
                        case 1://营业执照
                            yingyezheng.setImageBitmap(bitmap);
                            changebitmap(bitmap);
                            break;
                        case 2://营业照片
                            yingyezhaopian.setImageBitmap(bitmap);
                            changebitmap(bitmap);
                            break;

                    }

                }
                break;
            // 选择图片库的图片
            case PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap bit = ImageYS.getBitmapFormUri(FabushangjiActivity.this, uri);
                        switch (imagetap) {
                            case 0://项目图片
                                xiangmutupian.setImageBitmap(bitmap);
                                changebitmap(bit);
                                break;
                            case 1://营业执照
                                yingyezheng.setImageBitmap(bitmap);
                                changebitmap(bit);
                                break;
                            case 2://营业摘牌
                                yingyezhaopian.setImageBitmap(bitmap);
                                changebitmap(bit);
                                break;

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
