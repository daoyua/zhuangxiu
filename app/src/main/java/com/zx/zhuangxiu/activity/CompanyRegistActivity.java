package com.zx.zhuangxiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.zx.zhuangxiu.ECApplication;
import com.zx.zhuangxiu.ImageYS;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.automap.AutoMapAddressActivity;
import com.zx.zhuangxiu.model.ImageBean;
import com.zx.zhuangxiu.model.RegisteBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 企业注册界面
 */
public class CompanyRegistActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBack;
    private RadioButton mRb1, mRb2;
    private EditText mFuWuCount, mTime;
    private TextView gongsi_zhuce;
//            edizhi;
    private EditText ename, ephone, ekongjian, ekoubei, ecname, eshenfennum;
    private ImageView iyingye, izshenfen, ifshenfen, iimage, ihead;
    private int yingyeint = 0, zshenfenint = 1, fshenfenint = 2, imageint = 3, headint = 4;
    private int imagetap;//判断是那个控件点击了0营业执照，1身份证正面，2身份正反面3企业、店铺照片4头像

    private String tapy = "3";//默认店铺

    private AlertDialog dialog;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库

    //地址选择开始
    private List<String> listsheng = new ArrayList<>();
    private List<String> listshi = new ArrayList<>();
    private List<String> listxian = new ArrayList<>();
    private ArrayAdapter<String> shengadapter;
    private ArrayAdapter<String> shiadapter;
    private ArrayAdapter<String> xianadapter;

    private Map<String, List<String>> map = new HashMap<>();
    private Map<String, List<String>> mapshi = new HashMap<>();

    private String stringsheng, stringshi, stringxian, stringyuangong;
    //地址选择结束
    private List<String> listyuangong = new ArrayList<>();
    private ArrayAdapter<String> adapteryungong;
    private String imageurl;
    private String Licenseurl;
    private String IDUrl;
    private String IDUrl1;
    private String ShopUrl;
    private String UserUrl;
    private String shooujihao;
    private String oppid = "";
    private String token = "";
    private TextView company_address;
    private String resultAdd;
    private String lat;
    private String lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_regist);

        Bundle bundle = getIntent().getExtras();
        shooujihao = bundle.getString("shooujihao");
        oppid = bundle.getString("oppid");
        token = bundle.getString("token");
        initView();
    }

    private void initView() {

        //法人身份证号码
        company_address = (TextView) findViewById(R.id.company_address);

        eshenfennum = (EditText) findViewById(R.id.eshenfennum);//法人身份证号码
        mFuWuCount = (EditText) findViewById(R.id.company_regist_count);//经营产品
        ename = (EditText) findViewById(R.id.ename);//姓名
//        edizhi = (TextView) findViewById(R.id.edizhi);//地址
        ephone = (EditText) findViewById(R.id.ephone);//电话
        mTime = (EditText) findViewById(R.id.company_regist_time);//注册时间
        ekongjian = (EditText) findViewById(R.id.ekongjian);//企业空间
        ekoubei = (EditText) findViewById(R.id.ekoubei);//口碑

        iyingye = (ImageView) findViewById(R.id.iyingye);//营业执照
        izshenfen = (ImageView) findViewById(R.id.izshenfen);//身份证正面
        ifshenfen = (ImageView) findViewById(R.id.ifshenfen);//身份证反面
        iimage = (ImageView) findViewById(R.id.iimage);//企业、店铺照片
        ihead = (ImageView) findViewById(R.id.ihead);//头像


        mBack = (TextView) findViewById(R.id.company_regist_back);
        mRb1 = (RadioButton) findViewById(R.id.company_regist_rb1);
        mRb2 = (RadioButton) findViewById(R.id.company_regist_rb2);

        gongsi_zhuce = (TextView) findViewById(R.id.gongsi_zhuce);//注册按钮


        findViewById(R.id.acom_ddress_rl).setOnClickListener(this);//地址
        iyingye.setOnClickListener(this);//营业执照
        izshenfen.setOnClickListener(this);//身份证正面
        ifshenfen.setOnClickListener(this); //身份证反面
        iimage.setOnClickListener(this);//企业、店铺照片
        ihead.setOnClickListener(this);//头像

        gongsi_zhuce.setOnClickListener(this);
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);
        mFuWuCount.setOnClickListener(this);
        mBack.setOnClickListener(this);

        mTime.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        mTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg();
                }
            }
        });


    }

    private final int ADDRESS = 222;// 选择地址

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.acom_ddress_rl:
                intent = new Intent(CompanyRegistActivity.this, AutoMapAddressActivity.class);
                startActivityForResult(intent, ADDRESS);
                break;
            case R.id.company_regist_rb1:
                tapy = "0";
                break;
            case R.id.company_regist_rb2:
                tapy = "1";
                break;
            case R.id.company_regist_back:
                this.finish();
                break;
            case R.id.iyingye:
                imagetap = 0;//判断是那个控件点击了0营业执照，1身份证正面，2身份正反面3企业、店铺照片4头像
                showDialogList();
                break;
            case R.id.izshenfen:
                imagetap = 1;//判断是那个控件点击了0营业执照，1身份证正面，2身份正反面3企业、店铺照片4头像
                showDialogList();
                break;
            case R.id.ifshenfen:
                imagetap = 2;//判断是那个控件点击了0营业执照，1身份证正面，2身份正反面3企业、店铺照片4头像
                showDialogList();
                break;
            case R.id.iimage:
                imagetap = 3;//判断是那个控件点击了0营业执照，1身份证正面，2身份正反面3企业、店铺照片4头像
                showDialogList();
                break;
            case R.id.ihead:
                imagetap = 4;//判断是那个控件点击了0营业执照，1身份证正面，2身份正反面3企业、店铺照片4头像
                showDialogList();
                break;
            case R.id.gongsi_zhuce:

                String address = company_address.getText().toString().trim();
                String nameString = ename.getText().toString().trim();
                String timeString = mTime.getText().toString().trim();
//                String addressString = edizhi.getText().toString().trim();
                String phoneString = ephone.getText().toString().trim();
                String shenfennumString = eshenfennum.getText().toString().trim();
                if(TextUtils.isEmpty(address)){
                    Toast.makeText(CompanyRegistActivity.this, "请填写地址", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(nameString)
                        || TextUtils.isEmpty(timeString)
//                        || TextUtils.isEmpty(addressString)
                        || TextUtils.isEmpty(phoneString)
                        || TextUtils.isEmpty(shenfennumString)
                        || TextUtils.isEmpty(Licenseurl)
                        || TextUtils.isEmpty(IDUrl)
                        || TextUtils.isEmpty(IDUrl1)
                        || TextUtils.isEmpty(ShopUrl) || TextUtils.isEmpty(tapy)) {
                    Toast.makeText(CompanyRegistActivity.this, "请填写完整必填信息或重新选择上传的图片", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!RegexUtils.isMobileExact(phoneString)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!RegexUtils.isIDCard18Exact(shenfennumString)) {
                    Toast.makeText(this, "请输入正确的身份证号", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url = URLS.regeste(2, shooujihao, oppid, token, nameString, phoneString, address, UserUrl, "", IDUrl + "," + IDUrl1
                        , "", Licenseurl, ShopUrl, "", ekongjian.getText().toString().trim(), ekoubei.getText().toString().trim(), tapy, mFuWuCount.getText().toString()
                        , "", "", "", "", "");
                OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<RegisteBean>() {
                    @Override
                    public void onSuccess(RegisteBean response) {
                        if (response.getResult() == 1) {
                            final int userId = response.getData().getUserId();
                            //获取sharedPreferences对象
                            SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                            //获取editor对象
                            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                            editor.putInt("userId", userId);
                            //提交
                            editor.commit();//提交修改
                            URLS.setUser_id(userId);
                            Toast.makeText(CompanyRegistActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(CompanyRegistActivity.this, HomeActivity.class);
                            intent.putExtra("UserID", userId);
                            startActivity(intent);
                            CompanyRegistActivity.this.finish();
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });


                break;
            default:
                break;
        }

    }


    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(CompanyRegistActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int m = monthOfYear + 1;
                CompanyRegistActivity.this.mTime.setText(year + "-" + m + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    //打开相机或图库
    private void showDialogList() {

        LayoutInflater inflater = LayoutInflater.from(CompanyRegistActivity.this);
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

    //系统回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case ADDRESS://地址
                if (resultCode == 888) {
//                    intent.putExtra("lat", myLocation.getLatitude());
//                    intent.putExtra("lon", myLocation.getLongitude());
//                    intent.putExtra("add", auto_edit.getText());
                    Bundle extras = data.getExtras();
                    resultAdd = extras.getString("add");
                    lat = extras.getString("lat");
                    lon = extras.getString("lon");
                    if (!TextUtils.isEmpty(resultAdd)) {
                        company_address.setText(resultAdd);
                    }

//                   Double lat= data.getDoubleExtra("lon",0);
                }
                break;
            case IMAGE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    switch (imagetap) {
                        case 0://营业执照
                            iyingye.setImageBitmap(bitmap);
                            break;
                        case 1://身份正反面
                            izshenfen.setImageBitmap(bitmap);
                            break;
                        case 2://身份证反面
                            ifshenfen.setImageBitmap(bitmap);
                            break;
                        case 3://企业。店铺照片
                            iimage.setImageBitmap(bitmap);
                            break;
                        case 4://头像
                            ihead.setImageBitmap(bitmap);
                            break;
                        default:
                            break;
                    }

                    changebitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap bit = ImageYS.getBitmapFormUri(CompanyRegistActivity.this, uri);
                        switch (imagetap) {
                            case 0://营业执照
                                iyingye.setImageBitmap(bitmap);
                                break;
                            case 1://身份正反面
                                izshenfen.setImageBitmap(bitmap);
                                break;
                            case 2://身份证反面
                                ifshenfen.setImageBitmap(bitmap);
                                break;
                            case 3://企业。店铺照片
                                iimage.setImageBitmap(bitmap);
                                break;
                            case 4://头像
                                ihead.setImageBitmap(bitmap);
                                break;
                            default:
                                break;
                        }
                        changebitmap(bit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
    /*
    *
    *  private String Licenseurl;
    private String IDUrl;
    private String IDUrl1;
    private String IDHoldUrl;
    private String UserUrl;
    * */

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
            case 0://营业执照
                upLoadImage(file);
                break;
            case 1://身份正反面
                upLoadImage(file);
                break;
            case 2://身份证反面
                upLoadImage(file);
                break;
            case 3://企业。店铺照片
                upLoadImage(file);
                break;
            case 4://头像
                upLoadImage(file);
                break;
            default:
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
                        case 0://营业执照
                            Licenseurl = imageurl;
                            break;
                        case 1://身份正反面
                            IDUrl = imageurl;
                            break;
                        case 2://身份证反面
                            IDUrl1 = imageurl;
                            break;
                        case 3://企业。店铺照片
                            ShopUrl = imageurl;
                            break;
                        case 4://头像
                            UserUrl = imageurl;
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {
                progressDialog.dismiss();
            }
        });

    }

    //得到图片的ID
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
                        switch (imagetap) {
                            case 0://营业执照
                                yingyeint = array.getInt(0);
                                break;
                            case 1://身份正反面
                                zshenfenint = array.getInt(0);
                                break;
                            case 2://身份证反面
                                fshenfenint = array.getInt(0);
                                break;
                            case 3://企业。店铺照片
                                imageint = array.getInt(0);
                                break;
                            case 4://头像
                                headint = array.getInt(0);
                                break;
                            default:
                                break;
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
