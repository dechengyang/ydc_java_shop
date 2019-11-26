package com.ydcjavashop.shop.account;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.ydc.config.ApiConfig;
import com.ydc.config.Constant;
import com.ydc.config.SharePreferenceKey;
import com.ydc.datarepository.sphelper.SharedPreferencesHelper;
import com.ydcjavashop.shop.MainActivity;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginView;
import com.ydcjavashop.shop.base.BaseActivity;
import com.ydcjavashop.shop.base.Feed;
import com.ydcjavashop.shop.base.mvp.factory.CreatePresenter;
import com.ydcjavashop.shop.util.ValidateUtils;
import com.ydcjavashop.shop.view.CheckEditTextEmptyButton;
import com.ydcjavashop.shop.view.ClearEditText;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by decheng.yang on 2018/2/22.
 */
@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter> implements ILoginView {
//    @Bind(R.id.til_phone)
//    TextInputLayout til_phone;
//    @Bind(R.id.til_pwd)
//    TextInputLayout til_pwd;
//    @Bind(R.id.edt_phone)
//    ClearEditText edt_phone;
//    @Bind(R.id.edt_pwd)
//    ClearEditText edt_pwd;
//    @Bind(R.id.btn_login)
//    CheckEditTextEmptyButton cbLogin;
//    @Bind(R.id.line_name)
//    View line_name;
//    @Bind(R.id.line_pwd)
//    View line_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(String msg, int progress) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showErrorMsg(String msg, String content) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void succeed(Feed feed) {
        TokenBean token= (TokenBean) feed.getData();
        SharedPreferencesHelper.getInstance(LoginActivity.this).put(SharePreferenceKey.TOKEN,
                token.getToken());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setData() {

    }

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login://登录
                if (isAllow()) {
                    loginWithPwd();

                }

                break;
        }
    }

    public boolean isAllow() {
        boolean flag = true;

        return flag;
    }



    private void loginWithPwd() {
        HashMap<String, String> paramsData = new HashMap<>();
        paramsData.put("appid", Constant.appId);
        paramsData.put("appsecret", Constant.SECRETKEY);
        paramsData.put("username", "18721568888");
        paramsData.put("password", "123456");
        getMvpPresenter().login(ApiConfig.ACTION_LOGIN_URL,paramsData);
    }
}
