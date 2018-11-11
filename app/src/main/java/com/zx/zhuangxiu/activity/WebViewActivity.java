package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zx.zhuangxiu.R;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends AppCompatActivity {

    private String url;
    private TextView mTitle;
    private WebView mWebView;
    private WebSettings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        url = getIntent().getStringExtra("url");
        initView();
        inidData();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle = findViewById(R.id.title);
        mTitle.setText("客服");
        mWebView = findViewById(R.id.webview);
        mSettings = mWebView.getSettings();
    }
    private void inidData(){
        mWebView.setWebChromeClient(new MyWebChormeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.requestFocusFromTouch();
        //设置支持JS交互
        mSettings.setJavaScriptEnabled(true);
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置自适应屏幕，两者合用
        mSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        // 支持自动加载图片
        mSettings.setLoadsImagesAutomatically(true);
        //设置编码格式
        mSettings.setDefaultTextEncodingName("utf-8");
        //设置缓存模式 根据cache-control决定是否从网络上取数据。
        mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        mSettings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        mSettings.setDatabaseEnabled(true);
        //开启 Application Caches 功能
        mSettings.setAppCacheEnabled(true);
        //缓存目录
        String cacheDirPath = getFilesDir().getAbsolutePath() + "appName";
        //设置  Application Caches 缓存目录
        mSettings.setAppCachePath(cacheDirPath);
        //webview定位
        mSettings.setGeolocationEnabled(true);
        //  加载https和http混合资源   // 保存cookie
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(mWebView, true);
        }
        mWebView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        WebViewActivity.this.finish();

    }

    class MyWebViewClient extends WebViewClient {
        private String startUrl;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (!url.startsWith("http://") && !url.startsWith("https://")) {

                try {
                    final Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                } catch (Exception e) {

                }
                return true;

            } else {
                if (startUrl != null && startUrl.equals(url)) {
                    view.loadUrl(url);
                    return true;
                }

            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            startUrl = url;
            super.onPageFinished(view, url);
        }

        //判断某App是否存在
        public boolean isAvilible(Context context, String packageName) {
            final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
            // 从pinfo中将包名字逐一取出，压入pName list中
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    pName.add(pn);
                }
            }
            return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
        }

        //跳转到某App
        public void launchAppDetail(Context context, String appPkg, String marketPkg) {
            try {
                if (TextUtils.isEmpty(appPkg))
                    return;
                Uri uri = Uri.parse("market://details?id=" + appPkg);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (!TextUtils.isEmpty(marketPkg))
                    intent.setPackage(marketPkg);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //是否在webview内加载页面
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.getUrl().toString());
            } else {
                view.loadUrl(request.toString());
            }
            return true;
        }

        // 处理断网
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            // 断网或者网络连接超时
            if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {
                view.loadUrl("about:blank"); // 避免出现默认的错误界面
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    class MyWebChormeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
        }

     /*   public void openFileChooser(ValueCallback<Uri> valueCallback, String s, String s1) {
            if (s.contains("image")) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/test/" + System.currentTimeMillis() + ".jpg");
                file.getParentFile().mkdirs();
                Uri uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 20);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);// 启动系统摄像
                startActivityForResult(intent, 30);
            }


        }*/

    /*    @SuppressLint("NewApi")
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            mUploadCallbackAboveL = valueCallback;
            String[] acceptTypes = fileChooserParams.getAcceptTypes();
            for (int i = 0; i < acceptTypes.length; i++) {
                String acceptType = acceptTypes[i];
                if (acceptType.contains("image")) {
                    imagePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "image");
                    if (!imagePath.exists()) imagePath.mkdirs();
                    newFile = new File(imagePath, System.currentTimeMillis() + "default_image.jpg");

                    //第二参数是在manifest.xml定义 provider的authorities属性
                    contentUri = FileProvider.getUriForFile(DetailsActivity.this, "com.example.android.fileprovider", newFile);

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //兼容版本处理，因为 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) 只在5.0以上的版本有效
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ClipData clip = ClipData.newUri(getContentResolver(), "A photo", contentUri);
                        intent.setClipData(clip);
                        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    } else if (acceptType.contains("video")) {
                        List<ResolveInfo> resInfoList =
                                getPackageManager()
                                        .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        for (ResolveInfo resolveInfo : resInfoList) {
                            String packageName = resolveInfo.activityInfo.packageName;
                            grantUriPermission(packageName, contentUri,
                                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        }
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    startActivityForResult(intent, 20);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);// 启动系统摄像

                    startActivityForResult(intent, 30);
                }
            }

//            return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            return true;
        }*/

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
        CookieManager.getInstance().removeSessionCookie();
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            mWebView = null;
        }
    }
}
