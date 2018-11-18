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
import android.widget.AdapterView;
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
import com.zx.zhuangxiu.adapter.GridViewAddImgesAdpter;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.ImageBean;
import com.zx.zhuangxiu.view.MyGridView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 首页-找工作-发布
 */
public class FaBuWorkActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView fabu_work_next;
    private EditText fabu_name, fabu_address, fabu_mianji, fabu_kaigongtime, fabu_wangongtime, fabu_zhiwei, fabu_tiaojian;
    private ImageView fabu_img;
    private TextView fabu_work_back;
    private AlertDialog dialog;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    private String imageurl;
    private EditText linkman;
    private EditText phone;
    private EditText education;
    private EditText treatment;
    private EditText money;
    private MyGridView gw;
    private List<String> datas;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter;
    private TextView gongzhong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_work);

        initView();
    }

    private void initView() {
        fabu_zhiwei = (EditText) findViewById(R.id.fabu_zhiwei);
        fabu_tiaojian = (EditText) findViewById(R.id.fabu_tiaojian);
        fabu_work_next = (TextView) findViewById(R.id.fabu_work_next);
        fabu_work_back = (TextView) findViewById(R.id.fabu_work_back);
        fabu_name = (EditText) findViewById(R.id.fabu_name);
        fabu_address = (EditText) findViewById(R.id.fabu_address);
        fabu_mianji = (EditText) findViewById(R.id.fabu_mianji);
        fabu_kaigongtime = (EditText) findViewById(R.id.fabu_kaigongtime);
        fabu_wangongtime = (EditText) findViewById(R.id.fabu_wangongtime);
        fabu_img = (ImageView) findViewById(R.id.fabu_img);
        gongzhong = (TextView) findViewById(R.id.gongzhong);

        linkman = findViewById(R.id.linkman);
        phone = findViewById(R.id.phone);
        education = findViewById(R.id.education);
        treatment = findViewById(R.id.treatment);
        money = findViewById(R.id.money);

        gw = findViewById(R.id.gw);
        datas = new ArrayList<>();
        gridViewAddImgesAdpter = new GridViewAddImgesAdpter(datas, this);
        gw.setAdapter(gridViewAddImgesAdpter);
        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                showdialog();
                showDialogList();
            }
        });


        fabu_img.setOnClickListener(this);
        fabu_work_next.setOnClickListener(this);
        fabu_work_back.setOnClickListener(this);

        fabu_kaigongtime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        fabu_kaigongtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg();
                }
            }
        });


        fabu_wangongtime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg1();
                    return true;
                }
                return false;
            }
        });
        fabu_wangongtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg1();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.fabu_work_next:
                postWork();


                break;
            case R.id.fabu_work_back:
                this.finish();
                break;
            case R.id.fabu_img:
                showDialogList();
                break;

        }
    }

    private void postWork() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < datas.size(); i++) {
            stringBuffer.append(datas.get(i) + ",");
        }
        imageurl = stringBuffer.toString();
        String nameString = fabu_name.getText().toString();
        String linkmanString = linkman.getText().toString();
        String phoneString = phone.getText().toString();
        String tiaojianString = fabu_tiaojian.getText().toString();
        String addressString = fabu_address.getText().toString();
        String educationString = education.getText().toString();
        String treatmentString = treatment.getText().toString();
        String moneyString = money.getText().toString();
        String mianjiString = fabu_mianji.getText().toString();
        String zhiweiString = fabu_zhiwei.getText().toString();
        String kString = fabu_kaigongtime.getText().toString();
        String wString = fabu_wangongtime.getText().toString();
        String s = gongzhong.getText().toString();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(FaBuWorkActivity.this, "项目名称还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(addressString)) {
            Toast.makeText(FaBuWorkActivity.this, "项目地址还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(linkmanString)) {
            Toast.makeText(FaBuWorkActivity.this, "联系人还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(phoneString)) {
            Toast.makeText(FaBuWorkActivity.this, "联系电话还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(tiaojianString)) {
            Toast.makeText(FaBuWorkActivity.this, "招工内容还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(moneyString)) {
            Toast.makeText(FaBuWorkActivity.this, "工资还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(kString)) {
            Toast.makeText(FaBuWorkActivity.this, "开工时间还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(kString)) {
            Toast.makeText(FaBuWorkActivity.this, "完工时间还没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(zhiweiString)) {
            Toast.makeText(FaBuWorkActivity.this, "填写公司名称或个人", Toast.LENGTH_LONG).show();
            return;
        }

//                if (!TextUtils.isEmpty(imageurl)
//                        && !TextUtils.isEmpty(nameString)
//                        && !TextUtils.isEmpty(linkmanString)
//                        && !TextUtils.isEmpty(phoneString)
//                        && !TextUtils.isEmpty(tiaojianString)
//                        && !TextUtils.isEmpty(addressString)
//                        && !TextUtils.isEmpty(educationString)
//                        && !TextUtils.isEmpty(treatmentString)
//                        && !TextUtils.isEmpty(moneyString)
//                        && !TextUtils.isEmpty(mianjiString)
//                        && !TextUtils.isEmpty(zhiweiString)
//                        && !TextUtils.isEmpty(kString)
//                        && !TextUtils.isEmpty(wString)
//                        && !TextUtils.isEmpty(s)
//                        ) {
                    /*
                    *
                    *
                    *  +"userId="+user_id
                +"&name="+name
                +"&linkman="+linkman
                +"&telenumber ="+phone
                +"&address="+address
                +"&acreage="+acreage
                +"&synopsis="+synopsis
                +"&jobRequire="+jobRequire
                +"&education="+education
                +"&treatment="+treatment
                +"&wages="+wages
                +"&imgUrl="+imgUrl
                +"&startTime="+startTime
                +"&endTime="+endTime;
                    *
                    * */
        String url = URLS.SaveJob();

        FormBody formBody;
        FormBody.Builder add = new FormBody.Builder()
                .add("userId", URLS.getUser_id() + "")
                .add("name", nameString)
                .add("linkman", linkmanString)
                .add("telenumber", phoneString)
                .add("address", addressString)
                .add("acreage", mianjiString)
                .add("synopsis", zhiweiString)
                .add("jobRequire", tiaojianString)
                .add("education", educationString)
                .add("treatment", treatmentString)
                .add("wages", moneyString)

                .add("detailUrl", imageurl)
                .add("startTime", kString)
                .add("endTime", wString)
                .add("worktypes", s);
        if (datas.size() > 0) {
            add.add("imgUrl", datas.get(0));
        }

        formBody = add.build();


        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                if (response.getResult() == 1) {
                    Toast.makeText(FaBuWorkActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }


    private void showDialogList() {
        LayoutInflater inflater = LayoutInflater.from(FaBuWorkActivity.this);
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
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case IMAGE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
//                    fabu_img.setImageBitmap(bitmap);
//                    photoPath(bitmap);
                    changebitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap bit = ImageYS.getBitmapFormUri(FaBuWorkActivity.this, uri);
//                        photoPath(bit);
                        changebitmap(bit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }


    public void photoPath(String path) {
        datas.add(path);
        gridViewAddImgesAdpter.notifyDataSetChanged();
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
//                    imageurl = imageBean.getData().getUrl();
                    photoPath(imageBean.getData().getUrl());
                    progressDialog.dismiss();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(FaBuWorkActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int a = monthOfYear + 1;
                FaBuWorkActivity.this.fabu_kaigongtime.setText(year + "-" + a + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    //设置完工时间
    protected void showDatePickDlg1() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(FaBuWorkActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int a = monthOfYear + 1;
                FaBuWorkActivity.this.fabu_wangongtime.setText(year + "-" + a + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

}
