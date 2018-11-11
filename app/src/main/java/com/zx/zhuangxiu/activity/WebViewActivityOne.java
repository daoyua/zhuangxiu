package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.R;

import org.w3c.dom.Text;

public class WebViewActivityOne extends AppCompatActivity implements View.OnClickListener{
    private TextView back;
    private WebView mWebview;
    private WebSettings mWebSettings;
    private TextView loading;
    private String imagUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(this);

//        initView();
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
//        back.setOnClickListener(this);
//
//        mWebview = (WebView) findViewById(R.id.webView1);
//        loading = (TextView) findViewById(R.id.text_Loading);
//
//        mWebSettings = mWebview.getSettings();
//
//            mWebview.loadUrl("http://www.365webcall.com/chat/newChatWin3.aspx?h=&settings=mw7mPmbm7PNm60z3A76w7Iz3APP6mz3A66mmP6&LL=0");
//            Toast.makeText(WebViewActivityOne.this,"正在联系客服，请稍后",Toast.LENGTH_LONG).show();
//
//
//        //设置不用系统浏览器打开,直接显示在当前Webview
////        mWebview.setWebViewClient(new WebViewClient() {
////            @Override
////            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                view.loadUrl(url);
////                return true;
////            }
////        });
//
//        //设置WebChromeClient类
//        mWebview.setWebChromeClient(new WebChromeClient() {
//
//            //获取加载进度
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    String progress = newProgress + "%";
//                    loading.setText(progress);
//                } else if (newProgress == 100) {
//                    String progress = newProgress + "%";
//                    loading.setVisibility(View.GONE);
//                }
//            }
//        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                this.finish();
                break;
        }

    }

//    //销毁Webview
//    @Override
//    protected void onDestroy() {
//        if (mWebview != null) {
//            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            mWebview.clearHistory();
//
//            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
//            mWebview.destroy();
//            mWebview = null;
//        }
//        super.onDestroy();
//    }
}
