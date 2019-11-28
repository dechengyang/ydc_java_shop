package com.ydcjavashop.shop.account;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
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
import com.ydcjavashop.shop.MainActivity;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by decheng.yang on 2018/2/22.
 */
@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends AbstractBaseMvpFragmentActivity<ILoginMvpView, LoginPresenter> implements ILoginMvpView {

    @Bind(R.id.title)
    TitleView title;
    @Bind(R.id.ll_titile_vive)
    LinearLayout ll_titile_vive;
    @Bind(R.id.tv_kuaiji)
    TextView tv_kuaiji;
    @Bind(R.id.line_kuaiji)
    View line_kuaiji;
    @Bind(R.id.tv_pwd)
    TextView tv_pwd;
    @Bind(R.id.line_pwd)
    View line_pwd;
    @Bind(R.id.ll_pwd_value)
    LinearLayout ll_pwd_value;
    @Bind(R.id.ll_code)
    LinearLayout ll_code;
    @Bind(R.id.tv_forgetpwd)
    TextView tv_forgetpwd;

    @Bind(R.id.ll_agreement)
    LinearLayout ll_agreement;
    @Bind(R.id.edt_phone)
    EditText edt_phone;
    @Bind(R.id.edt_pwd)
    EditText edt_pwd;
    @Bind(R.id.edt_code)
    EditText edt_code;
    @Bind(R.id.tv_code_btn)
    TextView tv_code_btn;
    @Bind(R.id.ck_set)
    CheckBox ck_set;
    @Bind(R.id.btn_login)
    CheckEditTextEmptyButton cbLogin;

    private int loginType = 1;//0:密码登录 1:验证码登录 2：第三方登录


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
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
        TokenBean token= (TokenBean) feed.getData();
        SharedPreferencesHelper.getInstance(LoginActivity.this).put(SharePreferenceKey.TOKEN,
                token.getToken());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void initTitle() {
        title.setTitle("登录");
        title.setTColor(0xff333333);
        title.setLeftImageButton(R.mipmap.back);
        title.getLeftTitleView().setVisibility(View.VISIBLE);
        title.setRightTextButton("注册");
        title.setRightTextCorlr(0xff999999);
        title.getRightTitleView().setVisibility(View.VISIBLE);
        title.getRightTitleView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, RegistersActivity.class);
//                startActivity(intent);
            }
        });
        title.getLeftTitleView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enterHome();
            }
        });
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

    @OnClick({R.id.btn_login, R.id.tv_code_btn, R.id.tv_userxieyi, R.id.ll_pwd, R.id.rl_kuaijie_login, R.id.tv_forgetpwd, R.id.iv_weixin, R.id.iv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login://登录
                if (loginType == 0) {
                    if (isAllow()) {

                        loginWithPwd();

                    }
                } else if (loginType == 1) {

                    if (isAllowCodeLogin()) {

                    }

                }

                break;
            case R.id.tv_code_btn:


                break;
            case R.id.tv_userxieyi:

                break;
            case R.id.ll_pwd:
                loginType = 0;
                refreshView();
                tv_pwd.setTextColor(0xff333333);
                tv_kuaiji.setTextColor(0xff999999);
                line_kuaiji.setVisibility(View.GONE);
                line_pwd.setVisibility(View.VISIBLE);
                ll_agreement.setVisibility(View.GONE);
                ll_code.setVisibility(View.GONE);
                ll_pwd_value.setVisibility(View.VISIBLE);
                break;

            case R.id.rl_kuaijie_login:
                loginType = 1;
                refreshView();
                ll_code.setVisibility(View.VISIBLE);
                ll_pwd_value.setVisibility(View.GONE);
                tv_pwd.setTextColor(0xff999999);
                tv_kuaiji.setTextColor(0xff333333);
                line_kuaiji.setVisibility(View.VISIBLE);
                line_pwd.setVisibility(View.GONE);
                ll_agreement.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_forgetpwd:

                break;

            case R.id.iv_weixin:

                break;
            case R.id.iv_qq:


                break;
        }
    }

    private void refreshView() {
        if (loginType == 0) {
            if (TextUtils.isEmpty(edt_phone.getText()) || TextUtils.isEmpty(edt_pwd.getText().toString())) {
                cbLogin.setEditText(edt_phone, edt_pwd);
            } else {
                cbLogin.setEditText(edt_phone, edt_pwd);
                cbLogin.AutoExecuteonTextChanged();
                //cbLogin.invalidate();
            }

        } else if (loginType == 1) {
            if (TextUtils.isEmpty(edt_phone.getText()) || TextUtils.isEmpty(edt_code.getText().toString())) {
                cbLogin.setEditText(edt_phone, edt_code);
            } else {
                cbLogin.setEditText(edt_phone, edt_code);
                cbLogin.AutoExecuteonTextChanged();
                //cbLogin.invalidate();
            }

        }
    }

    public boolean isAllowCode() {
        boolean flag = true;
        try {
            if (TextUtils.isEmpty(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            } else if (!ValidateUtil.checkPhoneNumber(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isAllowCodeLogin() {
        boolean flag = true;
        try {
            if (TextUtils.isEmpty(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            } else if (!ValidateUtil.checkPhoneNumber(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            }
            if (TextUtils.isEmpty(edt_code.getText().toString().trim())) {
                Toast.makeText(this, "验证码不能为空!", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isAllow() {
        boolean flag = true;
        try {
            if (TextUtils.isEmpty(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            } else if (!ValidateUtil.checkPhoneNumber(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            }
            if (TextUtils.isEmpty(edt_pwd.getText().toString().trim())) {
                Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            } else {
//                if (loginType == 0) {
//                    if (edt_pwd.getText().toString().trim().length() < 6 || edt_pwd.getText().toString().trim().length() > 16) {
//                        Toast.makeText(this, "请输入6-16位字母或数字的密码!", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }



    private void loginWithPwd() {
        HashMap<String, String> paramsData = new HashMap<>();
        paramsData.put("appid", Constant.appId);
        paramsData.put("appsecret", Constant.SECRETKEY);
        paramsData.put("username", edt_phone.getText().toString().trim());
        paramsData.put("password", edt_pwd.getText().toString().trim());
        getMvpPresenter().login(ApiConfig.ACTION_LOGIN_URL,paramsData);
    }
}
