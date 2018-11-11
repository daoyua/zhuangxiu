package com.zx.zhuangxiu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.R;
public class MyDingDanGRChanPinActivity extends AppCompatActivity {
    private TextView back;
    private WebView mWebview;
    private WebSettings mWebSettings;
    private String kdname;
    private String kdnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dan_grchan_pin);
        Bundle bundle = getIntent().getExtras();
        kdname=bundle.getString("kdname");
        kdnum=bundle.getString("kdnum");
        mWebview=(WebView)findViewById(R.id.webview);
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initView();

    }
    //销毁Webview
    @Override
    protected void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
    private void initView() {
        /* 设置为使用webview推荐的窗口 */
        mWebview.getSettings().setUseWideViewPort(true);
        /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        mWebview.getSettings().setLoadWithOverviewMode(true);

        /* 设置支持Js,必须设置的,不然网页基本上不能看 */
        mWebview.getSettings().setJavaScriptEnabled(true);

        String kdurl="https://m.kuaidi100.com/index_all.html?type="+kdname+"&postid="+kdnum+"#result";
        //https://m.kuaidi100.com/index_all.html?type=[快递公[快递公司编码]&postid=[快递单号]&callbackurl=[点击"返回"跳转的地址]
        Log.d("徐康康",kdurl);
        mWebview.loadUrl(kdurl);
        Toast.makeText(MyDingDanGRChanPinActivity.this,"正在查询，请稍后",Toast.LENGTH_LONG).show();

        //设置不用系统浏览器打开,直接显示在当前Webview
//        mWebview.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                    view.loadUrl(url);
//                    return true;
//                }
//        });
    }
}
