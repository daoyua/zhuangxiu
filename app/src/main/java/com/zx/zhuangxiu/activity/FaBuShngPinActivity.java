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
import com.blankj.utilcode.util.RegexUtils;
import com.zx.zhuangxiu.ECApplication;
import com.zx.zhuangxiu.ImageYS;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.ImageBean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FaBuShngPinActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回
     */
    private TextView mBack;
    /**
     * 请输入商品名称
     */
    private EditText mName;
    /**
     * 请输入商品介绍
     */
    private EditText mSimple;
    /**
     * 请输入商品价位
     */
    private EditText mPrice;
    /**
     * 请输入数量
     */
    private EditText mNum;
    /**
     * 请输入有效期
     */
    private EditText mTime;
    /**
     * 请输入手机号
     */
    private EditText mPhone;
    private ImageView mProduct;
    /**
     * 提交
     */
    private TextView mShangjifabuTijao;
    private AlertDialog dialog;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    private String imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_shng_pin);
        initView();
    }

    private void initView() {
        mBack = (TextView) findViewById(R.id.tv_back);
        mBack.setOnClickListener(this);
        mName = (EditText) findViewById(R.id.et_name);
        mSimple = (EditText) findViewById(R.id.et_simple);
        mPrice = (EditText) findViewById(R.id.et_price);
        mNum = (EditText) findViewById(R.id.et_num);
        mTime = (EditText) findViewById(R.id.et_time);
        mPhone = (EditText) findViewById(R.id.et_phone);
        mProduct = (ImageView) findViewById(R.id.img_product);
        mShangjifabuTijao = (TextView) findViewById(R.id.shangjifabu_tijao);
        mShangjifabuTijao.setOnClickListener(this);
        mProduct.setOnClickListener(this);

        initData();
    }

    private void initData() {

        mTime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg(mTime);
                    return true;
                }
                return false;
            }
        });
        mTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg(mTime);
                }
            }
        });
    }

    //打开相机或图库
    @SuppressLint("WrongConstant")
    private void showDialogList() {

        LayoutInflater inflater = LayoutInflater.from(this);
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

    //设置开工时间
    protected void showDatePickDlg(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int a = monthOfYear + 1;
                editText.setText(year + "-" + a + "-" + dayOfMonth);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.shangjifabu_tijao:
                sendData();
                break;
            case R.id.img_product:
                showDialogList();
                break;
        }
    }

    public void sendData() {
        String nameString = mName.getText().toString();
        String simpleString = mSimple.getText().toString();
        String priceString = mPrice.getText().toString().trim();
        String numString = mNum.getText().toString().trim();
        String timeString = mTime.getText().toString();
        String phoneString = mPhone.getText().toString().trim();
        if (TextUtils.isEmpty(nameString) ||
                TextUtils.isEmpty(simpleString) ||
                TextUtils.isEmpty(priceString) ||
                TextUtils.isEmpty(numString) ||
                TextUtils.isEmpty(timeString) ||
                TextUtils.isEmpty(phoneString)) {
            Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(imageurl)) {
            Toast.makeText(this, "请上传商品图片", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtils.isMobileExact(phoneString)) {
            Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.get(URLS.addBusiness(URLS.getUser_id(),
                nameString,
                simpleString,
                priceString,
                Long.parseLong(numString),
                imageurl,timeString,
                Long.parseLong(phoneString)), new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                if (response.getResult()==1){
                    Toast.makeText(FaBuShngPinActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }

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

        upLoadImage(file);
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
                }
            }

            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
            }
        });
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
                    mProduct.setImageBitmap(bitmap);
                    changebitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap bit = ImageYS.getBitmapFormUri(this, uri);
                        mProduct.setImageBitmap(bitmap);
                        changebitmap(bit);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
