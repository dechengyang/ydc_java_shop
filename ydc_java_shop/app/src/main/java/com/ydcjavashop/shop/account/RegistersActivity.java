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
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginView;
import com.ydcjavashop.shop.base.BaseActivity;
import com.ydcjavashop.shop.base.mvp.factory.CreatePresenter;
import com.ydcjavashop.shop.util.ValidateUtils;
import com.ydcjavashop.shop.view.CheckEditTextEmptyButton;
import com.ydcjavashop.shop.view.ClearEditText;
import com.ydcjavashop.shop.view.TitleView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by decheng.yang on 2018/2/23.
 */
@CreatePresenter(LoginPresenter.class)
public class RegistersActivity extends BaseActivity<ILoginView, LoginPresenter> implements ILoginView {
    @Bind(R.id.title)
    TitleView title;
    @Bind(R.id.til_phone)
    TextInputLayout til_phone;
    @Bind(R.id.til_pwd)
    TextInputLayout til_pwd;
    @Bind(R.id.edt_phone)
    ClearEditText edt_phone;
    @Bind(R.id.edt_code)
    ClearEditText edt_code;
    @Bind(R.id.btn_sign_in)
    CheckEditTextEmptyButton btn_sign_in;
    @Bind(R.id.line_name)
    View line_name;
    @Bind(R.id.line_pwd)
    View line_pwd;
    @Bind(R.id.tv_sendcoe)
    TextView tv_sendcoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        setTitle("注册");

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
        btn_sign_in.setEditText(edt_phone, edt_code);
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

        edt_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 1) {
                    if (edt_code.getText().length() < 4 || edt_code.getText().length() > 4) {
                        til_pwd.setError("验证码错误");
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
        edt_code.setOnFocusChangeListener(new View.
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

    @OnClick({R.id.btn_sign_in, R.id.tv_sendcoe})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                if (isAllow()) {
                    Intent intent = new Intent(RegistersActivity.this,RegistersSetPasswordActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.tv_sendcoe:
                if (isAllow2()) {
                   /* Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);*/
                }
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
            } else if (edt_code.getText().length() < 4 || edt_code.getText().length() > 4) {
                Toast.makeText(this, "请输入4位的验证码", Toast.LENGTH_SHORT).show();
                flag = false;
                return flag;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean isAllow2() {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    protected void setData() {

    }
}
