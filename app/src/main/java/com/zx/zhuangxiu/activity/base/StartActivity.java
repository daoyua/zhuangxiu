package com.zx.zhuangxiu.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.activity.PhoneLoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends Activity {

    private TextView start_time;
    private int time = 5;
    private Timer timer;
    private TimerTask task;
    Handler  handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    start_time.setText(time + "s");
                    break;
                case 2:
                    timer.cancel();
                    task.cancel();
                    timer = null;
                    task = null;

                    startActivity(new Intent(StartActivity.this, PhoneLoginActivity.class));
                    break;
            }


            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        start_time = findViewById(R.id.start_time);
        start_time.setText(time + "s");
//       new Handler().postDelayed(new Runnable() {
//           @Override
//           public void run() {
//                startActivity(new Intent(StartActivity.this,PhoneLoginActivity.class));
//           }
//       }, 5000);
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (time > 1) {
                    time--;
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(2);
                }

            }
        };
        timer.schedule(task, 0, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler=null;
    }
}
