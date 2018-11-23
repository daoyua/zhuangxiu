package com.zx.zhuangxiu;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class NotifitationUtils {
    private static NotificationCompat.Builder builder;
    private static Notification notification;
  public static void   initNotification(Application application) {

        NotificationManager notificationManager = (NotificationManager)application.getSystemService(Context.NOTIFICATION_SERVICE);
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
