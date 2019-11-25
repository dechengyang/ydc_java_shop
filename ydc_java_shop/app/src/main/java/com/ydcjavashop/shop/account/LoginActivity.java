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
import com.ydcjavashop.shop.MainActivity;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginView;
import com.ydcjavashop.shop.base.BaseActivity;
import com.ydcjavashop.shop.base.mvp.factory.CreatePresenter;
import com.ydcjavashop.shop.util.ValidateUtils;
import com.ydcjavashop.shop.view.CheckEditTextEmptyButton;
import com.ydcjavashop.shop.view.ClearEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by decheng.yang on 2018/2/22.
 */
@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter> implements ILoginView {
    @Bind(R.id.til_phone)
    TextInputLayout til_phone;
    @Bind(R.id.til_pwd)
    TextInputLayout til_pwd;
    @Bind(R.id.edt_phone)
    ClearEditText edt_phone;
    @Bind(R.id.edt_pwd)
    ClearEditText edt_pwd;
    @Bind(R.id.cb_login)
    CheckEditTextEmptyButton cbLogin;
    @Bind(R.id.line_name)
    View line_name;
    @Bind(R.id.line_pwd)
    View line_pwd;
    @Bind(R.id.tv_sign_in)
    TextView tv_sign_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pwd);
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
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        cbLogin.setEditText(edt_phone, edt_pwd);
        edt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    if (!ValidateUtils.checkPhoneNumber(s.toString())) {
                        til_phone.setError("手机号码错误");
                        til_phone.setErrorEnabled(true);
                    } else {
                        til_phone.setError("");
                        til_phone.setErrorEnabled(false);
                    }
                } else {
                    til_phone.setError("");
                    til_phone.setErrorEnabled(false);
                }


            }
        });

        edt_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 1) {
                    if (edt_pwd.getText().length() < 6 || edt_pwd.getText().length() > 16) {
                        til_pwd.setError("请输入6~16位的密码");
                        til_pwd.setErrorEnabled(true);
                    } else {
                        til_pwd.setError("");
                        til_pwd.setErrorEnabled(false);
                    }
                } else {
                    til_pwd.setError("");
                    til_pwd.setErrorEnabled(false);
                }


            }
        });

        edt_phone.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    line_name.setBackgroundColor(0xffe9546b);
                } else {
                    // 此处为失去焦点时的处理内容
                    line_name.setBackgroundColor(0xffe5e5e5);

                }
            }
        });
        edt_pwd.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    line_pwd.setBackgroundColor(0xffe9546b);
                } else {
                    // 此处为失去焦点时的处理内容
                    line_pwd.setBackgroundColor(0xffe5e5e5);
                }
            }
        });
    }

    @Override
    protected void setData() {

    }

    @OnClick({R.id.cb_login, R.id.tv_sign_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_login://登录
                if (isAllow()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.tv_sign_in:
                Intent intent = new Intent(LoginActivity.this, RegistersActivity.class);
                startActivity(intent);
                break;
        }
    }

    public boolean isAllow() {
        boolean flag = true;
        try {
            if (TextUtils.isEmpty(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            } else if (!ValidateUtils.checkPhoneNumber(edt_phone.getText().toString().trim())) {
                Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            } else if (edt_pwd.getText().length() < 6 || edt_pwd.getText().length() > 16) {
                Toast.makeText(this, "请输入6~16位的密码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
