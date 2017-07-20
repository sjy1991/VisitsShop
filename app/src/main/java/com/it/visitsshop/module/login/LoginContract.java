package com.it.visitsshop.module.login;

import com.it.visitsshop.base.BasePresenter;
import com.it.visitsshop.base.BaseView;

/**
 * Created by he on 17-7-9.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter{
        void login(String userName, String passWord);
    }

    interface View extends BaseView<Presenter>{
        void onLogin(String userName, String passWord, boolean success, String msg);
    }
}
