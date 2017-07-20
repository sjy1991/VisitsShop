package com.it.visitsshop.module.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.it.visitsshop.module.main.MainActivity;
import com.it.visitsshop.R;
import com.it.visitsshop.base.BaseActivity;
import com.it.visitsshop.module.model.entity.LoginInfo;
import com.it.visitsshop.module.model.entity.User;
import com.it.visitsshop.utils.GsonUtil;
import com.it.visitsshop.utils.SPUtil;

import org.litepal.crud.DataSupport;

import static com.it.visitsshop.R.id.et_pwd;
import static com.it.visitsshop.R.id.et_user;

public class LoginActivity extends BaseActivity implements View.OnClickListener,
        LoginContract.View {


    private ImageView mImageView;
    private EditText mEtUser;
    private TextInputLayout mTilUser;
    private EditText mEtPwd;
    private TextInputLayout mTilPwd;
    private Button mBtnLogin;
    private LoginContract.Presenter mPresenter;
    private RelativeLayout mRl_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mPresenter = new LoginPresenter(this);
        String user_id = SPUtil.getInstance().getString("user_id");
        if (user_id != null) {
            startActivity(this, MainActivity.class, true);
        }
    }

    @Override
    protected void initView() {

        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setOnClickListener(this);
        mEtUser = (EditText) findViewById(et_user);
        mEtUser.setOnClickListener(this);
        mTilUser = (TextInputLayout) findViewById(R.id.til_user);
        mTilUser.setOnClickListener(this);
        mEtPwd = (EditText) findViewById(et_pwd);
        mEtPwd.setOnClickListener(this);
        mTilPwd = (TextInputLayout) findViewById(R.id.til_pwd);
        mTilPwd.setOnClickListener(this);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mRl_loading = (RelativeLayout) findViewById(R.id.rl_loading);

        mEtPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTilPwd.setErrorEnabled(false);
            }
        });

        mEtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mTilUser.setErrorEnabled(false);

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                boolean success = checkNull();
                if (success) {
                    mRl_loading.setVisibility(View.VISIBLE);
                    String user = mEtUser.getText().toString().trim();
                    String pwd = mEtPwd.getText().toString().trim();
                    mPresenter.login(user, pwd);
                }
                break;
        }
    }

    /**
     * 检查数据
     */
    private boolean checkNull() {
        // validate
        String user = mEtUser.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            setErrorMsg(mTilUser, "用户名不能为空");
            return false;
        }

        if (user.length() > 6) {
            setErrorMsg(mTilUser, "用户名必须在0~6位数之间");
            return false;
        }

        String pwd = mEtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            setErrorMsg(mTilPwd, "密码不能为空");
            return false;
        }

        if (pwd.length() > 6) {
            setErrorMsg(mTilPwd, "密码必须在0~6位数之间");
            return false;
        }
        mRl_loading.setVisibility(View.VISIBLE);
        // TODO validate success, do something
        return true;

    }

    private void setErrorMsg(TextInputLayout layout, String msg) {
        layout.setError(msg);
        layout.setErrorEnabled(true);
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void onLogin(String userName, String passWord, boolean success, String msg) {
        if (success) {
            LoginInfo loginInfo = GsonUtil.getInstance().fromJson(msg, LoginInfo.class);
            // 用户信息存入系统
            if (loginInfo.getCode() == 0) {
                LoginInfo.BodyBean body = loginInfo.getBody();
                SPUtil.getInstance().setString("user_id", body.getUserid());
                DataSupport.deleteAll(User.class);
                User user = new User();
                user.setUserId(body.getUserid());
                user.setArea(body.getArea());
                user.setImg(body.getImg());
                user.setJob(body.getJob());
                user.setNickName(body.getNickname());
                user.setPhoneNum(body.getPhonenum());
                user.setSex(body.getSex());
                user.save();
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(this, MainActivity.class);

            }
        }else {
            Toast.makeText(this, "网络连接失败!" + msg, Toast.LENGTH_SHORT).show();
        }
        mRl_loading.setVisibility(View.GONE);
    }
}
