package com.it.visitsshop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by he on 17-7-7.
 */

public abstract class BaseFragment extends Fragment{

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = getRootLayout(inflater, container);
        initViews(rootView);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    protected abstract View getRootLayout(LayoutInflater inflater, ViewGroup container);

    public abstract void initViews(View rootView);
}
