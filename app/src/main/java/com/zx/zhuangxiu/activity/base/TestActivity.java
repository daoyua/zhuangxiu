package com.zx.zhuangxiu.activity.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.autoupdatesdk.AppUpdateInfo;
import com.baidu.autoupdatesdk.AppUpdateInfoForInstall;
import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.CPCheckUpdateCallback;
import com.baidu.autoupdatesdk.CPUpdateDownloadCallback;
import com.baidu.autoupdatesdk.UICheckUpdateCallback;
import com.blankj.utilcode.util.LogUtils;
import com.zx.zhuangxiu.R;

import java.io.File;
import java.io.IOException;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtLog;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ac);
        findViewById(R.id.btn_ui).setOnClickListener(this);
        findViewById(R.id.btn_silence).setOnClickListener(this);
        findViewById(R.id.btn_noui).setOnClickListener(this);
        txtLog = (TextView) findViewById(R.id.txt_log);
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ui:
                if (getContactPermission()) {
                txtLog.setText("");
                dialog.show();
                BDAutoUpdateSDK.uiUpdateAction(this, new MyUICheckUpdateCallback());
                }
                break;
            case R.id.btn_silence:
                txtLog.setText("");
                BDAutoUpdateSDK.silenceUpdateAction(this);
                break;
            case R.id.btn_noui:
                txtLog.setText("");
                dialog.show();
                BDAutoUpdateSDK.cpUpdateCheck(this, new MyCPCheckUpdateCallback());
                break;
            default:
                break;
        }
    }


    private boolean getContactPermission() {
        int back = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE);
        int back1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_SETTINGS);

        //查看是否获取到相关权限
        if (back != PackageManager.PERMISSION_GRANTED) {
            //如果没有获取到权限
            ActivityCompat.requestPermissions(TestActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_SETTINGS}, 10000);
            /*向系统获取权限，10000是请求码，以便于下面进行验证时，区别不同的权限请求*/
            return false; /*当前整个方法的作用是检查当前是否已经获得权限，所以这里只是申请了权限，但是并不知道到底能不能获取成功，所以返回 false ，并且需要实现OnRequestPermissionResult()来再次验证此次的获取结果并进行下一步操作。*/
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        dialog.dismiss();
        super.onDestroy();
    }

    private class MyUICheckUpdateCallback implements UICheckUpdateCallback {

        @Override
        public void onNoUpdateFound() {

        }

        @Override
        public void onCheckComplete() {
            dialog.dismiss();
        }

    }

    private class MyCPCheckUpdateCallback implements CPCheckUpdateCallback {

        @Override
        public void onCheckUpdateCallback(final AppUpdateInfo info, AppUpdateInfoForInstall infoForInstall) {
            if (infoForInstall != null && !TextUtils.isEmpty(infoForInstall.getInstallPath())) {
                txtLog.setText(txtLog.getText() + "\n install info: " + infoForInstall.getAppSName() + ", \nverion="
                        + infoForInstall.getAppVersionName() + ", \nchange log=" + infoForInstall.getAppChangeLog());
                txtLog.setText(txtLog.getText() + "\n we can install the apk file in: "
                        + infoForInstall.getInstallPath());
                BDAutoUpdateSDK.cpUpdateInstall(getApplicationContext(), infoForInstall.getInstallPath());
            } else if (info != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                long size = info.getAppPathSize() > 0 ? info.getAppPathSize() : info.getAppSize();
                builder.setTitle(info.getAppVersionCode() + ", " + byteToMb(size))
                        .setMessage(Html.fromHtml(info.getAppChangeLog()))
                        .setNeutralButton("普通升级", null)
//                        .setPositiveButton("智能升级", null)
                        .setCancelable(info.getForceUpdate() != 1)
                        .setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (keyCode == KeyEvent.KEYCODE_BACK) {
                                    return true;
                                }
                                return false;
                            }
                        });
                if (info.getForceUpdate() != 1) {
                    builder.setNegativeButton("暂不升级", null);
                }
                final AlertDialog dialog = builder.show();
//                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(
//                        new View.OnClickListener() {
//
//                            @Override
//                            public void onClick(View v) {
//                                BDAutoUpdateSDK.cpUpdateDownloadByAs(TestActivity.this);
//                                dialog.dismiss();
//                            }
//                        });
                dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BDAutoUpdateSDK.cpUpdateDownload(TestActivity.this, info, new UpdateDownloadCallback());
                        dialog.dismiss();
                    }
                });
            } else {
                txtLog.setText(txtLog.getText() + "\n no update.");
            }

            dialog.dismiss();
        }

        private String byteToMb(long fileSize) {
            float size = ((float) fileSize) / (1024f * 1024f);
            return String.format("%.2fMB", size);
        }

    }

    public static void setPermission(String filePath) {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class UpdateDownloadCallback implements CPUpdateDownloadCallback {

        @Override
        public void onDownloadComplete(String apkPath) {
//            setPermission(apkPath);
            txtLog.setText(txtLog.getText() + "\n onDownloadComplete: " + apkPath);
//            txtLog.setText(txtLog.getText() + "\n onDownloadComplete: " + apkPath);
            LogUtils.e("\n onDownloadComplete: " + apkPath);
//            BDAutoUpdateSDK.cpUpdateInstall(getApplicationContext(), apkPath);
            installNormal(getApplicationContext(), apkPath);
        }

        @Override
        public void onStart() {
            txtLog.setText(txtLog.getText() + "\n Download onStart");
            LogUtils.e("\n Download onStart");
        }

        @Override
        public void onPercent(int percent, long rcvLen, long fileSize) {
            txtLog.setText(txtLog.getText() + "\n Download onPercent: " + percent + "%");
            LogUtils.e("\n Download onPercent: " + percent + "%");
        }

        @Override
        public void onFail(Throwable error, String content) {
            txtLog.setText(txtLog.getText() + "\n Download onFail: " + content);
            LogUtils.e("\n Download onFail: " + content);
        }

        @Override
        public void onStop() {
            txtLog.setText(txtLog.getText() + "\n Download onStop");
            LogUtils.e("\n Download onStop");
        }

    }

    public void installApk(Activity ctx, String installPath) {
//        Intent intent = new Intent(Intent.ACTION_VIEW); // 由于没有在Activity环境下启动Activity,设置下面的标签
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(Uri.fromFile(new File(installPath)), "application/vnd.android.package-archive");
//        ctx.startActivity(intent);


        {
            if (TextUtils.isEmpty(installPath)) {
                Toast.makeText(ctx, "bdp_update_install_file_not_exist", 0).show();
            } else {
                File f = new File(installPath);
                if (!f.exists()) {
                    Toast.makeText(ctx, "bdp_update_install_file_not_exist", 0).show();
                } else {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (Build.VERSION.SDK_INT >= 24) {
                        String authorities = ctx.getPackageName() + ".Fileprovider";
                        Uri apkUri = FileProvider.getUriForFile(ctx, authorities, f);
//                        intent.addFlags(1);
                        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    } else {
                        intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive");
                    }

                    if (!(ctx instanceof Activity)) {
//                        intent.addFlags(268435456);
                    }

                    ctx.startActivity(intent);
                }
            }
        }
    }

    //普通安装
    private static void installNormal(Context context, String apkPath) {
        Log.e("apkPath:",apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            File file = (new File(apkPath)); // 由于没有在Activity环境下启动Activity,设置下面的标签
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.zx.zhuangxiu.FileProvider", file); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
