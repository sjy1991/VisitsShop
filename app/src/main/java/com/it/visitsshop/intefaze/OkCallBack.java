package com.it.visitsshop.intefaze;

import java.io.IOException;

import okhttp3.Request;

/**
 * 访问网络回调接口
 * Created by he on 17-7-12.
 */

public interface OkCallBack {
    void onSuccessful(String finalMsg);
    void onFailure(Request request, IOException e);
}
