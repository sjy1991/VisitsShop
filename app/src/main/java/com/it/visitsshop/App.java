package com.it.visitsshop;

import android.app.Application;

import com.it.visitsshop.utils.SPUtil;

import org.litepal.LitePal;

/**
 * Created by he on 17-7-9.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        SPUtil.init(this);
    }
}
