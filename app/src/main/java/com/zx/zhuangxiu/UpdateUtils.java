package com.zx.zhuangxiu;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.baidu.autoupdatesdk.AppUpdateInfo;
import com.baidu.autoupdatesdk.AppUpdateInfoForInstall;
import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.CPCheckUpdateCallback;
import com.baidu.autoupdatesdk.CPUpdateDownloadCallback;
import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.io.IOException;

public class UpdateUtils {
    private Context myContext;
    private ProgressDialog dialog;
    private NotificationCompat.Builder builder;
    private Notification notification;
    private NotificationManager notificationManager;


    public void UpdataApk(Context context) {
        myContext = context;
        dialog = new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.show();

        BDAutoUpdateSDK.cpUpdateCheck(context, new MyCPCheckUpdateCallback());
    }

    private class MyCPCheckUpdateCallback implements CPCheckUpdateCallback {

        @Override
        public void onCheckUpdateCallback(final AppUpdateInfo info, AppUpdateInfoForInstall infoForInstall) {
            if (infoForInstall != null && !TextUtils.isEmpty(infoForInstall.getInstallPath())) {
                LogUtils.e("\n install info: " + infoForInstall.getAppSName() + ", \nverion="
                        + infoForInstall.getAppVersionName() + ", \nchange log=" + infoForInstall.getAppChangeLog());
                LogUtils.e("\n we can install the apk file in: "
                        + infoForInstall.getInstallPath());
                BDAutoUpdateSDK.cpUpdateInstall(myContext, infoForInstall.getInstallPath());
            } else if (info != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
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
//                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener( //智能升级
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

                        BDAutoUpdateSDK.cpUpdateDownload(myContext, info, new UpdateDownloadCallback());
                        dialog.dismiss();
                    }
                });
            } else {
                LogUtils.e("\n no update.");
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
//            txtLog.setText(txtLog.getText() + "\n onDownloadComplete: " + apkPath);
            LogUtils.e("\n onDownloadComplete: " + apkPath);
//            BDAutoUpdateSDK.cpUpdateInstall(getApplicationContext(), apkPath);
            installNormal(myContext, apkPath);
            builder.setContentText("下载完成:");
            notification = builder.build();
            notificationManager.notify(1, notification);
        }

        @Override
        public void onStart() {
            LogUtils.e("\n Download onStart");
            initNotification(ECApplication.getApplication());
        }

        @Override
        public void onPercent(int percent, long rcvLen, long fileSize) {
            LogUtils.e("\n Download onPercent: " + percent + "%");
            builder.setProgress(100, (int) (percent), false);
            builder.setContentText("下载进度:" + percent + "%");
            notification = builder.build();
            notificationManager.notify(1, notification);
        }

        @Override
        public void onFail(Throwable error, String content) {
            LogUtils.e("\n Download onFail: " + content);
        }

        @Override
        public void onStop() {
            LogUtils.e("\n Download onStop");
        }

    }


    //普通安装
    private  void installNormal(Context context, String apkPath) {
        notificationManager.cancel(1);
        Log.e("apkPath:", apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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
    private void initNotification(Application application) {

        notificationManager = (NotificationManager)application.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(application,"baidu");
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(application.getResources(), R.drawable.logo)) //设置通知的大图标
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        //构建通知对象
        notification = builder.build();

    }
}
