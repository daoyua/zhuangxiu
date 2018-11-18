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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.zx.zhuangxiu.adapter.SpinnerAdapter;
import com.zx.zhuangxiu.model.FWBean;
import com.zx.zhuangxiu.model.ImageBean;
import com.zx.zhuangxiu.view.MyGridView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class FaBuFuWuActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回
     */
    private TextView mFabuWorkBack;
    /**
     * 项目名称(请10个字内)
     */
    private EditText mTvName;
    private MyGridView mGw;
    private ImageView mFabuImg;
    /**
     * 项目地址
     */
    private EditText mTvAddress;
    /**
     * 项目面积（平米），若无请写0
     */
    private EditText mTvArea;
    /**
     * 请填写项目价格
     */
    private EditText mTvPrice;
    /**
     * 请选择所需类型
     */
    private EditText mTvType;
    private Spinner mSpinner;

    /**
     * 请选择供需类型
     */
    private EditText mTvType1;
    private Spinner mSpinner1;
    /**
     * 请填写其他需求
     */
    private EditText mTvOther;
    /**
     * 开始时间
     */
    private EditText mTvStartime;
    /**
     * 结束时间时间
     */
    private EditText mTvEndtime;
    /**
     * 提交
     */
    private TextView mTvSubmit;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter;
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<FWBean.DataBean.ClassListBean> mList = new ArrayList<>();
    private ArrayList<String> mList1 = new ArrayList<>();
    private AlertDialog dialog;

    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    //    private ArrayAdapter<FWBean.DataBean.ClassListBean> arr_adapter;
    private SpinnerAdapter arr_adapter;
    private ArrayAdapter<String> arr_adapter1;
    private String imageurl;


    int anInt = 21;
    int aa = 0;
    private EditText tv_phone;
    private RelativeLayout fabu_fuwu_type;
    private TextView fabu_fuwu_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_fu_wu);
        aa = getIntent().getIntExtra("type", 0);
        initView();
    }

    private void initView() {
        fabu_fuwu_type = (RelativeLayout) findViewById(R.id.fabu_fuwu_type);
        fabu_fuwu_title = (TextView) findViewById(R.id.fabu_fuwu_title);
        if (aa == 1) {
            fabu_fuwu_type.setVisibility(View.GONE);
            fabu_fuwu_title.setText("发布需求");
        }
        mFabuWorkBack = (TextView) findViewById(R.id.fabu_work_back);
        tv_phone = (EditText) findViewById(R.id.tv_phone);
        mTvName = (EditText) findViewById(R.id.tv_name);
        mGw = (MyGridView) findViewById(R.id.gw);
        mFabuImg = (ImageView) findViewById(R.id.fabu_img);
        mTvAddress = (EditText) findViewById(R.id.tv_address);
        mTvArea = (EditText) findViewById(R.id.tv_area);
        mTvPrice = (EditText) findViewById(R.id.tv_price);
        mTvType = (EditText) findViewById(R.id.tv_type);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mTvType1 = (EditText) findViewById(R.id.tv_type1);
        mSpinner1 = (Spinner) findViewById(R.id.spinner1);
        mTvOther = (EditText) findViewById(R.id.tv_pther);
        mTvStartime = (EditText) findViewById(R.id.tv_startime);
        mTvStartime.setOnClickListener(this);
        mTvEndtime = (EditText) findViewById(R.id.tv_endtime);
        mTvEndtime.setOnClickListener(this);
        mTvSubmit = (TextView) findViewById(R.id.tv_submit);
        mTvSubmit.setOnClickListener(this);
        mFabuWorkBack.setOnClickListener(this);

        datas = new ArrayList<>();
        gridViewAddImgesAdpter = new GridViewAddImgesAdpter(datas, this);
        mGw.setAdapter(gridViewAddImgesAdpter);
        mGw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showDialogList();
            }
        });

        mTvStartime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg(mTvStartime);
                    return true;
                }
                return false;
            }
        });
        mTvStartime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg(mTvStartime);
                }
            }
        });
        mTvEndtime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg(mTvEndtime);
                    return true;
                }
                return false;
            }
        });
        mTvEndtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg(mTvEndtime);
                }
            }
        });


        mList1.add("供应型");
        mList1.add("需求型");

        OkHttpUtils.get(URLS.HTTP + "/api/server/serverList", new OkHttpUtils.ResultCallback<FWBean>() {
            @Override
            public void onSuccess(FWBean response) {
                if (response.getResult() == 1) {
                    List<FWBean.DataBean.ClassListBean> classList = response.getData().getClassList();
                    mList.addAll(classList);
                    arr_adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

//        arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mList);
        arr_adapter = new SpinnerAdapter(this, mList);

//        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(arr_adapter);
        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                mTvType.setText(mList.get(arg2).getCname());
                anInt = mList.get(arg2).getId();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                mTvType.setText("");
            }
        });

        arr_adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList1);
        arr_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(arr_adapter1);
//        mSpinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int arg2, long arg3) {
//                mTvType1.setText(mList1.get(arg2));
//                switch (mList1.get(arg2)) {
//                    case "供应型":
//                        aa = 0;
//                        break;
//                    case "需求型":
//                        aa = 1;
//                        break;
//                }
//            }

//            public void onNothingSelected(AdapterView<?> arg0) {
//            }
//        });
    }

    //设置时间
    protected void showDatePickDlg(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(FaBuFuWuActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int a = monthOfYear + 1;
                editText.setText(year + "-" + a + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }


    private void showDialogList() {
        LayoutInflater inflater = LayoutInflater.from(FaBuFuWuActivity.this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_startime:
                break;
            case R.id.tv_endtime:
                break;
            case R.id.tv_submit:
                Submit();
                break;
            case R.id.fabu_work_back:
                finish();
                break;
        }
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

    public void photoPath(String path) {
        datas.add(path);
        gridViewAddImgesAdpter.notifyDataSetChanged();
    }

    public void Submit() {
        Log.e("TAG", "anInt" + anInt + ">>>>>>aa" + aa);
        String phone = tv_phone.getText().toString().trim();
        String nameString = mTvName.getText().toString().trim();
        String addressString = mTvAddress.getText().toString().trim();//公司地址
        String areaString = mTvArea.getText().toString().trim();
        String priceString = mTvPrice.getText().toString().trim();
        String otherString = mTvOther.getText().toString().trim();
        String startString = mTvStartime.getText().toString().trim();
        String endString = mTvEndtime.getText().toString().trim();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < datas.size(); i++) {
            stringBuffer.append(datas.get(i) + ",");
        }
        imageurl = stringBuffer.toString();

        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(FaBuFuWuActivity.this, "公司名称没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(FaBuFuWuActivity.this, "联系电话没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(addressString)) {
            Toast.makeText(FaBuFuWuActivity.this, "公司地址没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(otherString)) {
            Toast.makeText(FaBuFuWuActivity.this, "服务内容没填写", Toast.LENGTH_LONG).show();
            return;
        }
//        if(TextUtils.isEmpty(nameString)){
//            Toast.makeText(FaBuFuWuActivity.this, "公司名称没填写", Toast.LENGTH_LONG).show();
//            return;
//        }
        if (TextUtils.isEmpty(startString)) {
            Toast.makeText(FaBuFuWuActivity.this, "起始时间没填写", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(endString)) {
            Toast.makeText(FaBuFuWuActivity.this, "结束没填写", Toast.LENGTH_LONG).show();
            return;
        }

//        if (!TextUtils.isEmpty(nameString)
//                && !TextUtils.isEmpty(addressString)
//                && !TextUtils.isEmpty(areaString)
//                && !TextUtils.isEmpty(priceString)
//                && !TextUtils.isEmpty(otherString)
//                && !TextUtils.isEmpty(startString)
//                && !TextUtils.isEmpty(endString)
//                && !TextUtils.isEmpty(imageurl)
//                ) {
        String fabu = URLS.fabu(URLS.getUser_id(), nameString, addressString, areaString, priceString, otherString, imageurl, anInt, startString, endString, aa, phone);
//            FormBody formBody = new FormBody.Builder()
//                    .add("userId", URLS.getUser_id() + "")
//                    .add("name", nameString)
//                    .add("address", addressString)
//                    .add("area", areaString)
//                    .add("price", priceString)
//                    .add("requires", otherString)
//                    .add("imgUrl", imageurl)
//                    .add("serverType", anInt + "")
//                    .add("startTime", startString)
//                    .add("endTime", endString)
//                    .add("isNeed ", aa + "")
//                    .build();
        OkHttpUtils.get(fabu, new OkHttpUtils.ResultCallback<Fab>() {
            @Override
            public void onSuccess(Fab response) {
                if (response.getResult() == 1) {
                    Toast.makeText(FaBuFuWuActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                    FaBuFuWuActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

          /*  OkHttpUtils.post(fabu, formBody, new OkHttpUtils.ResultCallback<Fab>() {
                @Override
                public void onSuccess(Fab response) {
                    if (response.getResult()==1){
                        Toast.makeText(FaBuFuWuActivity.this, "发布成功", Toast.LENGTH_LONG).show();
                        FaBuFuWuActivity.this.finish();
                    }

                }

                @Override
                public void onFailure(Exception e) {

                }
            });*/
//        } else {
//            Toast.makeText(this, "请完善服务需求", Toast.LENGTH_SHORT).show();
//        }
//
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
                        Bitmap bit = ImageYS.getBitmapFormUri(FaBuFuWuActivity.this, uri);
//                        photoPath(bit);
                        changebitmap(bit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
