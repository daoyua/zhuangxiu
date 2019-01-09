package com.zx.zhuangxiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.zx.zhuangxiu.adapter.PersonGridViewAdapter;
import com.zx.zhuangxiu.model.Guo;
import com.zx.zhuangxiu.model.ImageBean;
import com.zx.zhuangxiu.model.RegisteBean;
import com.zx.zhuangxiu.utils.LocalJsonResolutionUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 个人注册界面
 */
public class PersonRegistActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView person_regist_back;
    //    private GridView mGridView;
    private TextView geren_zhuce;
    private EditText mTime, time, mAge;
    private PersonGridViewAdapter personGridViewAdapter;
    private List<String> mList;
    private AlertDialog dialog;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    private final int ADDRESS = 222;// 选择地址
    private int imagetap;//判断是那个控件点击了
    private String gz = "石材类";
    //地址选择开始
    private Spinner ssheng, sshi, sxian;
    private List<String> listsheng = new ArrayList<>();
    private List<String> listshi = new ArrayList<>();
    private List<String> listxian = new ArrayList<>();
    private ArrayAdapter<String> shengadapter;
    private ArrayAdapter<String> shiadapter;
    private ArrayAdapter<String> xianadapter;

    private Map<String, List<String>> map = new HashMap<>();
    private Map<String, List<String>> mapshi = new HashMap<>();

    private String stringsheng, stringshi, stringxian;
    //地址选择结束
    private EditText ename, ephone, egerenjienng, egerenkongjian, ekoubei, shenfennum;
    private ImageView izshenfen, ifshenfen, isshenfen, ihead;
    private int zshenfenint = 0;
    private int fshenfenint = 0;
    private int sshenfenint = 0;
    private int headint = 0;
    private String gz1 = null;
    private String gz2 = null;
    private String gz3 = null;
    private String gz4 = null;
    private String gz5 = null;
    private CheckBox checkboxone, checkboxtwo, checkboxthree, checkboxfour, checkboxfive;
    private String imageurl;
    private String IDUrl;
    private String IDUrl1;
    private String IDHoldUrl;
    private String UserUrl;
    private String shooujihao;
    RadioButton nan, nv;
    String sex = "";
    private String oppid = "";
    private String token = "";
    private TextView register_person_address_tv;
    private String lat;
    private String lon;
    private String resultAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_regist);

        Bundle bundle = getIntent().getExtras();
        shooujihao = bundle.getString("shooujihao");
        oppid = bundle.getString("oppid");
        token = bundle.getString("token");
        initView();
    }

    private void initView() {
        //省
        register_person_address_tv = findViewById(R.id.register_person_address_tv);
        ssheng = (Spinner) findViewById(R.id.ssheng);//省
        sshi = (Spinner) findViewById(R.id.sshi);//市
        sxian = (Spinner) findViewById(R.id.sxian);//县
        mAge = findViewById(R.id.age);
        mTime = (EditText) findViewById(R.id.person_time);
        time = (EditText) findViewById(R.id.regedit_time);//注册年限
        nan = findViewById(R.id.nan);
        nv = findViewById(R.id.nv);

        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        time.setText(simpleDateFormat.format(date) + "");
        ename = (EditText) findViewById(R.id.ename);//名字
        ephone = (EditText) findViewById(R.id.ephone);//电话
        shenfennum = (EditText) findViewById(R.id.shenfennum);//身份证号
        egerenjienng = (EditText) findViewById(R.id.egerenjienng);//个人技能
        egerenkongjian = (EditText) findViewById(R.id.egerenkongjian);//个人空间
        ekoubei = (EditText) findViewById(R.id.ekoubei);//口碑评价

        izshenfen = (ImageView) findViewById(R.id.izshenfen);//身份证正面
        ifshenfen = (ImageView) findViewById(R.id.ifshenfen);//身份证反面
        isshenfen = (ImageView) findViewById(R.id.isshenfen);//手持身份证
        ihead = (ImageView) findViewById(R.id.ihead);//头像

        checkboxone = (CheckBox) findViewById(R.id.checkboxone);
        checkboxtwo = (CheckBox) findViewById(R.id.checkboxtwo);
        checkboxthree = (CheckBox) findViewById(R.id.checkboxthree);
        checkboxfour = (CheckBox) findViewById(R.id.checkboxfour);
        checkboxfive = (CheckBox) findViewById(R.id.checkboxfive);


        person_regist_back = (TextView) findViewById(R.id.person_regist_back);

        geren_zhuce = (TextView) findViewById(R.id.geren_zhuce);
        nan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "男";
                }

            }
        });
        nv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "女";
                }

            }
        });

//        mGridView = (GridView) findViewById(R.id.person_regist_gridview);

        izshenfen.setOnClickListener(this);//身份证正面
        ifshenfen.setOnClickListener(this);//身份证反面
        isshenfen.setOnClickListener(this);//手持身份证
        ihead.setOnClickListener(this);//头像
        person_regist_back.setOnClickListener(this);
        findViewById(R.id.register_person_address_tv).setOnClickListener(this);
        geren_zhuce.setOnClickListener(this);
        time.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        checkboxone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkboxone.isChecked()) {
                    gz1 = "石材类";
                } else {
                    gz1 = null;
                }
            }
        });
        checkboxtwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkboxtwo.isChecked()) {
                    gz2 = " 装修类";
                } else {
                    gz2 = null;
                }
            }
        });
        checkboxthree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkboxthree.isChecked()) {
                    gz3 = " 安装维修";
                } else {
                    gz3 = null;
                }
            }
        });
        checkboxfour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkboxfour.isChecked()) {
                    gz4 = " 家政保养";
                } else {
                    gz4 = null;
                }
            }
        });
        checkboxfive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkboxfive.isChecked()) {
                    gz5 = " 其他类型";
                } else {
                    gz5 = null;
                }
            }
        });

        liandong();
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
                        case 0://身份证正面
                            IDUrl = imageurl;
                            break;
                        case 1://身份证反面
                            IDUrl1 = imageurl;
                            break;
                        case 2://手持身份证
                            IDHoldUrl = imageurl;
                            break;
                        case 3://头像
                            UserUrl = imageurl;
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_person_address_tv://地址
                Intent intent = new Intent(PersonRegistActivity.this, AutoMapAddressActivity.class);
                startActivityForResult(intent, ADDRESS);
                break;

                case R.id.person_regist_back:
                this.finish();
                break;
            case R.id.izshenfen://身份证 正面0
                imagetap = 0;
                showDialogList();
                break;
            case R.id.ifshenfen://身份证反面1
                imagetap = 1;
                showDialogList();
                break;
            case R.id.isshenfen://手持身份证2
                imagetap = 2;
                showDialogList();
                break;
            case R.id.ihead://头像3
                imagetap = 3;
                showDialogList();
                break;
            case R.id.geren_zhuce: //zhuce
                String nameString = ename.getText().toString().trim();
                String shenfennumString = shenfennum.getText().toString().trim();
                String phoneString = ephone.getText().toString().trim();
                String s = mTime.getText().toString();
                String age = mAge.getText().toString().trim();
                if (nan.isChecked()) {
                    sex = "男";
                }
                if (nv.isChecked()) {
                    sex = "女";
                }
                //|| TextUtils.isEmpty(shenfennumString) 身份证选填

                //|| TextUtils.isEmpty(IDHoldUrl)手持身份证
                //|| TextUtils.isEmpty(IDUrl1) 身份证正反
                if (TextUtils.isEmpty(nameString)) {//姓名
                    Toast.makeText(PersonRegistActivity.this, "请填写姓名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneString)) {//电话
                    Toast.makeText(PersonRegistActivity.this, "请填写电话", Toast.LENGTH_LONG).show();
                    return;
                }
//                if (TextUtils.isEmpty(nameString) || TextUtils.isEmpty(phoneString) || TextUtils.isEmpty(IDUrl) || TextUtils.isEmpty(IDHoldUrl) || TextUtils.isEmpty(IDUrl1)) {
//                    Toast.makeText(PersonRegistActivity.this, "请填写完整必填信息或重新选择上传的图片", Toast.LENGTH_LONG).show();
//                    return;
//                }
                if (TextUtils.isEmpty(gz1) && TextUtils.isEmpty(gz2) && TextUtils.isEmpty(gz3) && TextUtils.isEmpty(gz4) && TextUtils.isEmpty(gz5)) {
                    Toast.makeText(PersonRegistActivity.this, "请至少选择一个工种", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!RegexUtils.isMobileExact(phoneString)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(shenfennumString)) {//身份证号
                    if (!RegexUtils.isIDCard18Exact(shenfennumString)) {
                        Toast.makeText(this, "请输入正确的身份证号", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(this, "请输入工作年限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(sex)) {
                    Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(egerenjienng.getText().toString())) {
                    Toast.makeText(this, "请输入个人技能，简单介绍下你会什么", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(register_person_address_tv.getText().toString())) {
                    Toast.makeText(this, "请输入地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder workType = new StringBuilder();
                if (!TextUtils.isEmpty(gz1)) {
                    workType.append(gz1);
                }
                if (!TextUtils.isEmpty(gz2)) {
                    workType.append(gz2);
                }
                if (!TextUtils.isEmpty(gz3)) {
                    workType.append(gz3);
                }
                if (!TextUtils.isEmpty(gz4)) {
                    workType.append(gz4);
                }
                if (!TextUtils.isEmpty(gz5)) {
                    workType.append(gz5);
                }

//            String workType = gz1 + gz2 + gz3 + gz4 + gz5;
                String url = URLS.regeste(1, shooujihao, oppid, token, nameString, phoneString, resultAdd , UserUrl, workType.toString(),
                        IDUrl + "," + IDUrl1, IDHoldUrl, "", "",
                        egerenjienng.getText().toString(), egerenkongjian.getText().toString(),
                        ekoubei.getText().toString(), "", "",
                        sex, s, age, lon + "", lat + ""
                );
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
                            Toast.makeText(PersonRegistActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(PersonRegistActivity.this, HomeActivity.class);
                            intent.putExtra("UserID", userId);
                            startActivity(intent);
                            PersonRegistActivity.this.finish();
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });

                break;

        }

    }

   /* protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(PersonRegistActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int m = monthOfYear + 1;
                PersonRegistActivity.this.mTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }*/

    //打开相机或图库
    private void showDialogList() {
        LayoutInflater inflater = LayoutInflater.from(PersonRegistActivity.this);
        View view = inflater.inflate(R.layout.my_info_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        TextView tv_paizhao = view.findViewById(R.id.paizhao);
        TextView tv_xiangce = view.findViewById(R.id.bendixiangce);
        tv_paizhao.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
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
                        register_person_address_tv.setText(resultAdd);
                    }

//                   Double lat= data.getDoubleExtra("lon",0);
                }
                break;
            // 表示 调用照相机拍照
            case IMAGE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    switch (imagetap) {
                        case 0://身份证正面
                            izshenfen.setImageBitmap(bitmap);
                            changebitmap(bitmap);
                            break;
                        case 1://身份证反面
                            ifshenfen.setImageBitmap(bitmap);
                            changebitmap(bitmap);
                            break;
                        case 2://手持身份证
                            isshenfen.setImageBitmap(bitmap);
                            changebitmap(bitmap);
                            break;
                        case 3://头像
                            ihead.setImageBitmap(bitmap);
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
                        Bitmap bit = ImageYS.getBitmapFormUri(PersonRegistActivity.this, uri);
                        switch (imagetap) {
                            case 0://身份证正面
                                izshenfen.setImageBitmap(bitmap);
                                changebitmap(bit);
                                break;
                            case 1://身份证反面
                                ifshenfen.setImageBitmap(bitmap);
                                changebitmap(bit);
                                break;
                            case 2://手持身份证
                                isshenfen.setImageBitmap(bitmap);
                                changebitmap(bit);
                                break;
                            case 3://头像
                                ihead.setImageBitmap(bitmap);
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
            case 0://身份证正面
                upLoadImage(file);
                Log.e("TAG", "IDURL" + IDUrl);
                break;
            case 1://身份证反面
                upLoadImage(file);
                Log.e("TAG", "IDUrl1" + IDUrl1);
                break;
            case 2://手持身份证
                upLoadImage(file);
                Log.e("TAG", "IDHoldUrl" + IDHoldUrl);
                break;
            case 3://头像
                upLoadImage(file);
                Log.e("TAG", "UserUrl" + UserUrl);
                break;
            default:
                break;
        }

    }

    //选择地址开始
    public void liandong() {
        //得到本地json文本内容
        String fileName = "province.json";
        String foodJson = LocalJsonResolutionUtils.getJson(PersonRegistActivity.this, fileName);
        //转换为对象
        Guo guo = LocalJsonResolutionUtils.JsonToObject(foodJson, Guo.class);
        for (int i = 0; i < guo.getList().size(); i++) {
            listsheng.add(guo.getList().get(i).getName());//将省份添加到集合
            List<String> lists = new ArrayList<>();
            for (int a = 0; a < guo.getList().get(i).getCity().size(); a++) {//的到市的集合长度
                lists.add(guo.getList().get(i).getCity().get(a).getName());//将市添加到集合
                List<String> listx = new ArrayList<>();
                for (int b = 0; b < guo.getList().get(i).getCity().get(a).getArea().size(); b++) {
                    listx.add(guo.getList().get(i).getCity().get(a).getArea().get(b));//得到县的集合，
                }
                mapshi.put(guo.getList().get(i).getCity().get(a).getName(), listx); //然后将市和县连城map；
            }
            map.put(guo.getList().get(i).getName(), lists);//将省和市弄成map。《省，市集合》

        }

        spinerit();


    }

    public void spinerit() {
        shengadapter = new ArrayAdapter<String>(PersonRegistActivity.this, R.layout.spinner_item, R.id.tvSpinner, listsheng);//设置省的适配器
        shengadapter.setDropDownViewResource(R.layout.spinner_down_item);//设置省的下拉样式
        ssheng.setAdapter(shengadapter);//设置适配器
        ssheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringsheng = ssheng.getSelectedItem().toString();
                listshi = map.get(stringsheng);
                shiadapter = new ArrayAdapter<String>(PersonRegistActivity.this, R.layout.spinner_item, R.id.tvSpinner, listshi);//设置市的适配器
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
                stringshi = sshi.getSelectedItem().toString();
                listxian = mapshi.get(stringshi);
                xianadapter = new ArrayAdapter<String>(PersonRegistActivity.this, R.layout.spinner_item, R.id.tvSpinner, listxian);//设置县的适配器
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
                stringxian = sxian.getSelectedItem().toString();
                Log.d("徐康", stringsheng + "====" + stringshi + "====" + stringxian);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
