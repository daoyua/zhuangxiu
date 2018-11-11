package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login_tv1, login_tv2;
    private TextView login_back;
    private String shooujihao;
    private String oppid="";
    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            shooujihao = bundle.getString("shooujihao");
            oppid = bundle.getString("oppid");
            token = bundle.getString("token");
        }


        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
        int anInt = sharedPreferences.getInt("userId", -1);
        if (anInt!=-1){
            URLS.setUser_id(anInt);
            Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }else {
            initView();
        }

    }

    private void initView() {
        login_tv1 = (TextView) findViewById(R.id.login_tv1);
        login_tv2 = (TextView) findViewById(R.id.login_tv2);
        login_back = (TextView) findViewById(R.id.login_back);

        login_back.setOnClickListener(this);
        login_tv1.setOnClickListener(this);
        login_tv2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_tv1:
                showDialog();
                break;
            case R.id.login_tv2:
                showDialog2();
                break;
            case R.id.login_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    //跳转到工人注册
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        View v = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_tishi, null);
        builder.setView(v);
        final AlertDialog dialog = builder.show();
        v.findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this, "点击了好的...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, PersonRegistActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shooujihao",shooujihao);
                bundle.putString("oppid",oppid);
                bundle.putString("token",token);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        v.findViewById(R.id.dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this, "点击了关闭...", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager m = this.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.45); // 高度设置为屏幕的0.6，根据实际情况调整
        p.width = (int) (d.getWidth() * 0.6); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(p);

    }

//跳转到企业注册
    private void showDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        View v = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_tishi, null);
        builder.setView(v);
        final AlertDialog dialog = builder.show();
        v.findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this, "点击了好的...", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, CompanyRegistActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shooujihao",shooujihao);
                bundle.putString("oppid",oppid);
                bundle.putString("token",token);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        v.findViewById(R.id.dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(LoginActivity.this, "点击了关闭...", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager m = this.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.45); // 高度设置为屏幕的0.6，根据实际情况调整
        p.width = (int) (d.getWidth() * 0.6); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(p);

    }
}
