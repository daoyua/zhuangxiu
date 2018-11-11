package com.zx.zhuangxiu;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

import io.rong.imkit.RongIM;

public class ECApplication extends Application {
    private static ECApplication instance;
    public static ECApplication getApplication(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        init();
    }
    private void  init(){
        RongIM.init(this);
        MultiDex.install(this);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);

        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
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
