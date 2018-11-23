package com.zx.zhuangxiu;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

import io.rong.imkit.RongIM;

public class ECApplication extends Application {
    private static ECApplication instance;

    public static ECApplication getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initUpdate();

    }

    private void initUpdate() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelID = "1";
            String channelName = "channel_name";
            NotificationChannel channel = null;

            channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            Context applicationContext = getApplicationContext();
            NotificationManager manager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(applicationContext);
            builder.setContentText("tongzhi_content");
            builder.setContentTitle("tongzhi_title"); //创建通知时指定channelID builder.setChannelId(channelID); Notification notification = builder.build();
        }
    }

    private void init() {
        RongIM.init(this);
        MultiDex.install(this);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);

        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e("xkk", "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }


}
