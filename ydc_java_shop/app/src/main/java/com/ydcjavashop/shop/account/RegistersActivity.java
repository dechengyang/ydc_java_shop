package com.ydcjavashop.shop.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ydc.base.util.ToastUtil;
import com.ydc.base.util.ValidateUtil;
import com.ydc.base.view.CheckEditTextEmptyButton;
import com.ydc.base.view.TitleView;
import com.ydc.config.ApiConfig;
import com.ydc.config.Constant;
import com.ydc.config.SharePreferenceKey;
import com.ydc.datarepository.sphelper.SharedPreferencesHelper;
import com.ydc.mvp.factory.CreatePresenter;
import com.ydc.mvp.view.AbstractBaseMvpFragmentActivity;
import com.ydc.networkservice.bean.BaseFeed;
import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@CreatePresenter(LoginPresenter.class)
public class RegistersActivity extends AbstractBaseMvpFragmentActivity<ILoginMvpView, LoginPresenter> implements ILoginMvpView{

    @Bind(R.id.title)
    TitleView title;
    @Bind(R.id.ll_titile_vive)
    LinearLayout ll_titile_vive;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);


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
        ToastUtil.showShort(this, content);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void succeed(BaseFeed feed) {

    }

    @Override
    public void responseSucceed(Feed feed) {

    }


    @Override
    protected void initTitle() {
        setTitle("注册");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {

    }

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    private void refreshView() {
    }


}
