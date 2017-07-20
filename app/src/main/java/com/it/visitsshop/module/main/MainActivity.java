package com.it.visitsshop.module.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.it.visitsshop.R;
import com.it.visitsshop.base.BaseActivity;
import com.it.visitsshop.base.BaseFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private FrameLayout mFlContent;
    private TextView mTxtMenuBottomHome;
    private TextView mTxtMenuBottomShop;
    private TextView mTxtMenuBottomVisit;
    private TextView mTxtMenuBottomTrain;
    private TextView mTxtMenuBottomMe;
    private LinearLayout mLayoutMenuBottom;
    private BaseFragment mHomeFragmet;
    private BaseFragment mShopFragmet;
    private BaseFragment mMeFragmet;
    private BaseFragment mTrainFragmet;
    private BaseFragment mVisitFragmet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void initView() {
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mTxtMenuBottomHome = (TextView) findViewById(R.id.txt_menu_bottom_home);
        mTxtMenuBottomHome.setOnClickListener(this);
        mTxtMenuBottomShop = (TextView) findViewById(R.id.txt_menu_bottom_shop);
        mTxtMenuBottomShop.setOnClickListener(this);
        mTxtMenuBottomVisit = (TextView) findViewById(R.id.txt_menu_bottom_visit);
        mTxtMenuBottomVisit.setOnClickListener(this);
        mTxtMenuBottomTrain = (TextView) findViewById(R.id.txt_menu_bottom_train);
        mTxtMenuBottomTrain.setOnClickListener(this);
        mTxtMenuBottomMe = (TextView) findViewById(R.id.txt_menu_bottom_me);
        mTxtMenuBottomMe.setOnClickListener(this);
        mLayoutMenuBottom = (LinearLayout) findViewById(R.id.layout_menu_bottom);
        mLayoutMenuBottom.setOnClickListener(this);
        mHomeFragmet = new HomeFragment();
        mShopFragmet = new ShopFragment();
        mMeFragmet = new MeFragment();
        mTrainFragmet = new TrainFragment();
        mVisitFragmet = new VisitFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, mHomeFragmet, "home")
                .commitNow();
    }


    @Override
    public void onClick(View v) {

    }
}
