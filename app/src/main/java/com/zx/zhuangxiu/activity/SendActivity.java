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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.ImageBean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SendActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mBack;
    private ImageView mImg;
    private EditText mEdit;
    private Button mBtn;
    private AlertDialog dialog;
    private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
    private final int PICK = 1;// 选择图片库
    String imageUrl="";
    private int dataID;
    private int typeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        dataID = getIntent().getIntExtra("da", -1);
        typeID = getIntent().getIntExtra("type", -1);
        mBack = findViewById(R.id.back);
        mImg = findViewById(R.id.image);
        mEdit = findViewById(R.id.ed_com);
        mBtn = findViewById(R.id.btn_app);
        mBtn.setOnClickListener(this);
        mImg.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.image:
                showDialogList();
                break;
            case R.id.btn_app:
                upData(dataID, imageUrl);
                break;
        }
    }

    private void upData(int dataId, String imgUrl) {
        String content = mEdit.getText().toString();

        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("commentId", "")
                .add("dataId", dataId + "")
                .add("type", typeID + "")
                .add("content", content)
                .add("picUrl", imgUrl)
                .build();

        OkHttpUtils.post(URLS.HTTP + "/api/commentary/SaveCommentary", formBody, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                if (response.getResult() == 1) {
                    Toast.makeText(SendActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
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
        LayoutInflater inflater = LayoutInflater.from(SendActivity.this);
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
                    mImg.setImageBitmap(bitmap);
                    changebitmap(bitmap);
                }
                break;
            // 选择图片库的图片
            case PICK:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        Bitmap bit = ImageYS.getBitmapFormUri(SendActivity.this, uri);
                        mImg.setImageBitmap(bit);
                        changebitmap(bit);
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
//        getimageurl("head", file);
        upLoadImage(file);
    }

    /* 上传图片*/
    private void upLoadImage(File file) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
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
                    imageUrl = imageBean.getData().getUrl();
                }
            }

            @Override
            public void onFailure(Exception e) {
            progressDialog.dismiss();

            }
        });
    }

}
