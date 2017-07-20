package com.it.visitsshop.module.login;

import android.support.annotation.NonNull;

import com.it.visitsshop.intefaze.OkCallBack;
import com.it.visitsshop.intefaze.Param;
import com.it.visitsshop.net.Api;
import com.it.visitsshop.net.OkHttpManager;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by he on 17-7-9.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    LoginContract.View mView;

    public LoginPresenter(@NonNull LoginContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void login(final String userName, final String passWord) {
        Param param_user = new Param();
        param_user.key = "userid";
        param_user.value = "num01";
        Param param_pwd = new Param();
        param_pwd.key = "password";
        param_pwd.value = "123456";

        OkHttpManager.getInstance().doPost(Api.login, new OkCallBack() {
            @Override
            public void onSuccessful(String finalMsg) {
                mView.onLogin(userName, passWord, true, finalMsg);

            }

            @Override
            public void onFailure(Request request, IOException e) {
                mView.onLogin(userName, passWord, false, e.getMessage());
            }
        }, new Param[]{param_user, param_pwd});
    }
}
