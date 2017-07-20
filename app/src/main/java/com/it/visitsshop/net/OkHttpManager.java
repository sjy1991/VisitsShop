package com.it.visitsshop.net;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.it.visitsshop.intefaze.OkCallBack;
import com.it.visitsshop.intefaze.Param;
import com.it.visitsshop.utils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp管理类
 * 网络请求封装
 * Created by he on 17-7-12.
 */

public class OkHttpManager {

    private Gson mGson;
    private Handler mHandler;
    private OkHttpClient mClient;

    private static volatile OkHttpManager sInstance;

    private OkHttpManager(){
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
        OkHttpClient.Builder clienBuilder = new OkHttpClient.Builder()
                                            .connectTimeout(15, TimeUnit.SECONDS)
                                            .writeTimeout(20, TimeUnit.SECONDS)
                                            .readTimeout(20, TimeUnit.SECONDS);
        mClient = clienBuilder.build();

    }

    /**
     * 设置Get请求，提交
     * @param url
     * @param call
     */
    public void doGet(String url, OkCallBack call){
        Request.Builder requestBuild = new Request.Builder();
        Request request = requestBuild.url(url).build();
        doNetWork(request, call);


    }

    /**
     * 设置post请求，提交
     * @param url
     * @param params
     */
    public void doPost(String url, OkCallBack callBack,Param... params){
        if (null == params) {
            params = new Param[0];
        }

        FormBody.Builder body = new FormBody.Builder();

        for (Param param : params) {
            body.add(param.key, param.value);
        }

        Request.Builder request = new Request.Builder();
        Request build = request.url(url).post(body.build()).build();
        doNetWork(build , callBack);


    }

    /**
     * 执行网络请求
     * @param request
     * @param callBack
     */
    private void doNetWork(final Request request, final OkCallBack callBack) {
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                /* 主线程回调 */
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(request, e);
                        LogUtil.I(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String msg = "";
                int code = response.code();

                try {
                    msg = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final String finalMsg = msg;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccessful(finalMsg);
                    }
                });
            }
        });
    }

    /**
     * 双重检查锁单例
     * @return
     */
    public static OkHttpManager getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpManager.class){
                if (sInstance == null) {
                    sInstance = new OkHttpManager();
                }
            }
        }
        return sInstance;
    }








}
