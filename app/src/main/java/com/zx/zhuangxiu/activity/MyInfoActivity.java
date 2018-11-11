package com.zx.zhuangxiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PermissionUtils;
import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.ECApplication;
import com.zx.zhuangxiu.ImageYS;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.ali.DataCleanManager;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.ChengGongBean;
import com.zx.zhuangxiu.model.MydexinxiBean;
import com.zx.zhuangxiu.view.CircleImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRlNiCheng, mRlshouji, mRltouxiang, my;
    private CircleImageView mTouXiang;

    private List<MydexinxiBean> mlist = new ArrayList<>();
    private AlertDialog dialog;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    private int imageurl;
    private TextView textname, txtiphone, my_info_back, tuichudengluo, tv_phone ,login_address;
    private RelativeLayout zhifubaolayout;
    private RelativeLayout cleanhuancun;
    private Dialog dialogs;
    private TextView huancuntext, text;
    private RelativeLayout addresslayout;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x01:
                    dialogs.dismiss();
                    huancuntext.setText("0.0KB");
                    break;
                case 0x02:
                    dialogs.dismiss();
                    break;
            }
        }

        ;
    };
    private TextView version_name;
    private RelativeLayout my_info_acc;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getPerData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        //获得应用内部缓存(/data/data/com.example.androidclearcache/cache)
        File file = new File(this.getCacheDir().getPath());
        try {
            huancuntext.setText(DataCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();
        getPerData();
    }

    private void initView() {
        mRlNiCheng = (RelativeLayout) findViewById(R.id.my_info_nichengRl);
        my = (RelativeLayout) findViewById(R.id.my);
        mRlshouji = (RelativeLayout) findViewById(R.id.my_info_bangdingshouji);
        mRltouxiang = (RelativeLayout) findViewById(R.id.my_info_touxiangRl);
        mTouXiang = (CircleImageView) findViewById(R.id.my_info_touxiang);
        textname = (TextView) findViewById(R.id.textname);
        login_address = (TextView) findViewById(R.id.login_address);
        txtiphone = (TextView) findViewById(R.id.txtiphone);
        text = (TextView) findViewById(R.id.text);
        my_info_back = findViewById(R.id.my_info_back);
        tuichudengluo = findViewById(R.id.tuichudengluo);
        zhifubaolayout = findViewById(R.id.myinfo_zhifubao);
        cleanhuancun = findViewById(R.id.myinfo_qingchuhuancun);
        huancuntext = findViewById(R.id.myinfo_huancuntext);
        addresslayout = findViewById(R.id.myinfo_myaddress);
        version_name = findViewById(R.id.version_name);
        my_info_acc = findViewById(R.id.my_info_acc);
        tv_phone = findViewById(R.id.tv_phone);
        version_name.setText("v" + getVerName(this));
        tuichudengluo.setOnClickListener(this);
        mRlNiCheng.setOnClickListener(this);
        mRlshouji.setOnClickListener(this);
        mRltouxiang.setOnClickListener(this);
        my_info_back.setOnClickListener(this);
        zhifubaolayout.setOnClickListener(this);
        text.setOnClickListener(this);
        cleanhuancun.setOnClickListener(this);
        huancuntext.setOnClickListener(this);
        addresslayout.setOnClickListener(this);
        my_info_acc.setOnClickListener(this);
        my_info_acc.setVisibility(View.GONE);
        my.setVisibility(View.GONE);
    }

    public void initDialog() {

        ProgressDialog progressDialog = new ProgressDialog(MyInfoActivity.this);//1.创建一个ProgressDialog的实例
        progressDialog.setTitle("这是一个 progressDialog");//2.设置标题
        progressDialog.setMessage("正在加载中，请稍等......");//3.设置显示内容
        progressDialog.setCancelable(true);//4.设置可否用back键关闭对话框
        progressDialog.show();//5.将ProgessDialog显示出来
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                setResult(RESULT_OK);
                finish();
                return false;//拦截事件
        }

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my_info_nichengRl://更换昵称-
                intent.setClass(MyInfoActivity.this, GaiNichengActivity.class);
                startActivity(intent);
                break;

            case R.id.my_info_bangdingshouji://绑定手机号
                intent.setClass(MyInfoActivity.this, BindingPhoneActivity.class);
                startActivity(intent);
                break;

            case R.id.my_info_touxiangRl://更换头像
                showDialogList();
                break;
            case R.id.my_info_back://返回上层页面
                intent.setClass(MyInfoActivity.this, HomeActivity.class);
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.tuichudengluo://退出当前账号
                SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                //获取editor对象
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                editor.putInt("userId", -1);
                //提交
                editor.commit();//提交修改
                intent.setClass(MyInfoActivity.this, PhoneLoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.myinfo_zhifubao://绑定支付宝账号
                intent.setClass(MyInfoActivity.this, BindingZhifubaoActivity.class);
                startActivity(intent);
                break;
            case R.id.myinfo_qingchuhuancun://清除缓存
                Message msg = new Message();
                dialogs = createLoadingDialog(MyInfoActivity.this, "清理中....");
                dialogs.show();
                try {
                    DataCleanManager.cleanInternalCache(getApplicationContext());
                    msg.what = 0x01;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = 0x02;
                }
                handler.sendMessageDelayed(msg, 1000);
                break;

            case R.id.myinfo_myaddress:
                intent.setClass(MyInfoActivity.this, MylookaddressActivity.class);
                startActivity(intent);
                break;
            case R.id.my_info_acc:
                intent.setClass(MyInfoActivity.this, AddAccountAcitivity.class);
                startActivityForResult(intent, 1000);

                break;
            case R.id.text:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(login_address.getText());
                Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void getPerData() {
        String url = URLS.mydata(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MydexinxiBean>() {
            @Override
            public void onSuccess(MydexinxiBean response) {
                if (response.getResult() == 1) {
                    MydexinxiBean.DataBean data = response.getData();
                    if (data.getUserUrl().startsWith("http://") || data.getUserUrl().startsWith("https://")) {
                        Picasso.with(MyInfoActivity.this)
                                .load(data.getUserUrl())
                                .placeholder(R.mipmap.logo_zhanwei)
                                .error(R.mipmap.logo_zhanwei)
                                .fit()
                                .centerCrop()
                                .into(mTouXiang);

                    } else {
                        Picasso.with(MyInfoActivity.this)
                                .load(URLS.HTTP + data.getUserUrl())
                                .placeholder(R.mipmap.logo_zhanwei)
                                .error(R.mipmap.logo_zhanwei)
                                .fit()
                                .centerCrop()
                                .into(mTouXiang);

                    }
                    textname.setText(data.getNickname());
                    String telenumber = data.getTelenumber();
                    if (!TextUtils.isEmpty(telenumber) && telenumber.length() == 11)
                        txtiphone.setText(telenumber.substring(0, 3) + "****" + telenumber.substring(7, telenumber.length()));
                    tv_phone.setText(data.getUserPwd());
                    if (data.getAllow() == 0) {
                        my_info_acc.setVisibility(View.GONE);
                        my.setVisibility(View.GONE);

                    } else {
                        my_info_acc.setVisibility(View.VISIBLE);
                        my.setVisibility(View.VISIBLE);
                    }

                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });


    }


    private void showDialogList() {
        LayoutInflater inflater = LayoutInflater.from(MyInfoActivity.this);
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
                    mTouXiang.setImageBitmap(bitmap);
                    changebitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap bit = ImageYS.getBitmapFormUri(MyInfoActivity.this, uri);
                        mTouXiang.setImageBitmap(bit);
                        changebitmap(bit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 1000:
                getPerData();
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
//        getimageurl("head", file);
        upLoadImage(file);
    }

    /* 上传图片*/
    private void upLoadImage(File file) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在上传");
        progressDialog.show();
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", URLS.getUser_id() + "")
                .addFormDataPart("userUrl", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file));

        RequestBody requestBody = builder.build();

        OkHttpUtils.post(URLS.modify(), requestBody, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean imageBean) {
                if (imageBean.getResult() == 1) {
                    progressDialog.dismiss();
                    Toast.makeText(MyInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
            }
        });
    }

    public void getimageurl(String type, File file) {
        String url = URLS.toimage();
        RequestBody fileBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("type", type)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(fileBody)
                .build();
        okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(150, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String a = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(a);
                    String data = jsonObject.getString("data").toString();
                    JSONArray array = new JSONArray(data);
                    for (int i = 0; i < array.length(); i++) {
                        imageurl = array.getInt(0);
                    }
                    took(imageurl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void took(int i) {
        String url = URLS.changetouxiang(URLS.getUser_id(), i);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChengGongBean>() {
            @Override
            public void onSuccess(ChengGongBean response) {
                if (response.getResult() == 1) {
                    Toast.makeText(MyInfoActivity.this, "更改成功", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 清除缓存的dialog
     *
     * @param context
     * @param msg
     * @return
     */
    // 自定义的清理对话框
    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.dialog_img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
//        // 加载动画
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
//        // 使用ImageView显示动画
//        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.AppTheme);// 创建自定义样式dialog

        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
        return loadingDialog;

    }


}
