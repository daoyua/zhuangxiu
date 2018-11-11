package com.zx.zhuangxiu;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.zx.zhuangxiu.model.DetailsFive;
import com.zx.zhuangxiu.utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class OkHttpUtils {
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;

    private OkHttpUtils() {
        File cacheFile = new File(ECApplication.getApplication().getExternalCacheDir().toString(), "cache");
        //缓存大小为100M
        int cacheSize = 100 * 1024 * 1024;
        //创建缓存对象
        final Cache cache = new Cache(cacheFile, cacheSize);
        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(NetCacheInterceptor )//添加自定义缓存拦截器，注意这里需要使用.addNetworkInterceptor
                .addInterceptor(OfflineCacheInterceptor)
                /* .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)*/
                .build();
        mGson = new Gson();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    private synchronized static OkHttpUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpUtils();
        }
        return mInstance;
    }

    /**
     * 有网时候的缓存
     */
    static final Interceptor NetCacheInterceptor  = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            Response response = chain.proceed(request);

            if (NetWorkUtils.isNetworkConnected(ECApplication.getApplication().getApplicationContext())) {
                int maxAge = 1;//缓存失效时间，单位为秒
                //获取头部信息
                String cacheControl =request.cacheControl().toString();
                return response.newBuilder()
                        .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", "public ,max-age=" + maxAge)
//                        .header("Cache-Control", cacheControl)
                        .build();
            }
            return response;
        }
    };

    /**
     * 没有网时候的缓存
     */
    static final Interceptor OfflineCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetworkConnected(ECApplication.getApplication().getApplicationContext())) {
                int maxAge = Integer.MAX_VALUE;//缓存失效时间，单位为秒
                request = request.newBuilder()
//                        .cacheControl(new CacheControl
//                                .Builder()
//                                .maxStale(60,TimeUnit.SECONDS)
//                                .onlyIfCached()
//                                .build()
//                        ) 两种方式结果是一样的，写法不同
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxAge)
                        .build();
            }
            return chain.proceed(request);
        }
    };


    private void getRequest(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        Log.e("TAG","getUrl"+url);
        deliveryResult(callback, request);
    }

    private void postRequest(String url, final ResultCallback callback,RequestBody requestBody) {
        Request request = buildPostRequest(url, requestBody);
        Log.e("TAG","postUrl"+url);
        deliveryResult(callback, request);
    }

    /**
     * 处理结果
     * @param callback
     * @param request
     */
    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailCallback(callback, e);
                Log.e("TAG","fail>>>>>"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String str = response.body().string();
                    Log.e("TAG","response>>>>"+str);

                    if (callback.mType == String.class) {
                        /**
                         * 返回字符串
                         */
                        sendSuccessCallBack(callback, str);
                    } else {
                        /**
                         * 这里处理解析返回对象
                         */
                        Object object = mGson.fromJson(str, callback.mType);
                        sendSuccessCallBack(callback, object);

                    }
                } catch (final Exception e) {
                    sendFailCallback(callback, e);
                    Log.e("TAG","fail>>>>>"+e.getMessage());
                }
            }
        });
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final ResultCallback callback, final Object obj) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj);
                }
            }
        });
    }

    private Request buildPostRequest(String url, RequestBody requestBody) {
        return new Request.Builder().url(url).post(requestBody).build();
    }


    /**********************对外接口************************/

    /**
     * get请求
     * @param url  请求url
     * @param callback  请求回调
     */
    public static void get(String url, ResultCallback callback) {
        getmInstance().getRequest(url, callback);
    }

    /**
     * post请求
     * @param url       请求url
     * @param callback  请求回调
     * @param requestBody    请求参数
     */
    public static void post(String url,RequestBody requestBody,final ResultCallback callback) {
        getmInstance().postRequest(url, callback, requestBody);
    }

    /**
     * http请求回调类,回调方法在UI线程中执行
     * @param <T>
     */
    public static abstract class ResultCallback<T> {

        Type mType;

        public ResultCallback(){
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         * @param response
         */
        public abstract void onSuccess(T response);

        /**
         * 请求失败回调
         * @param e
         */
        public abstract void onFailure(Exception e);
    }

    /**
     * post请求参数类   这里可以根据项目抽取成泛型
     */
    public static class Param {

        String key;
        String value;

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }
}
