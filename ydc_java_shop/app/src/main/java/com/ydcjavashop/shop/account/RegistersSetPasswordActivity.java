package com.ydcjavashop.shop.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginView;
import com.ydcjavashop.shop.base.BaseActivity;
import com.ydcjavashop.shop.base.mvp.factory.CreatePresenter;
import com.ydcjavashop.shop.view.CheckEditTextEmptyButton;
import com.ydcjavashop.shop.view.ClearEditText;
import com.ydcjavashop.shop.view.TitleView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by decheng.yang on 2018/2/23.
 */
@CreatePresenter(LoginPresenter.class)
public class RegistersSetPasswordActivity extends BaseActivity<ILoginView, LoginPresenter> implements ILoginView {
    @Bind(R.id.title)
    TitleView title;
    @Bind(R.id.til_pwd)
    TextInputLayout til_pwd;
    @Bind(R.id.til_again_pwd)
    TextInputLayout til_again_pwd;
    @Bind(R.id.edt_pwd)
    ClearEditText edt_pwd;
    @Bind(R.id.edt_again_pwd)
    ClearEditText edt_again_pwd;
    @Bind(R.id.btn_save)
    CheckEditTextEmptyButton btn_save;
    @Bind(R.id.line_name)
    View line_name;
    @Bind(R.id.line_pwd)
    View line_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pwd);
        ButterKnife.bind(this);
        initView();
        setTitle("设置密码");

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

        btn_save.setEditText(edt_pwd,edt_again_pwd);
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

        edt_again_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 1) {
                    if (edt_again_pwd.getText().length() < 6 || edt_again_pwd.getText().length() > 16) {
                        til_again_pwd.setError("请输入6~16位的密码");
                        til_again_pwd.setErrorEnabled(true);
                    } else {
                        til_again_pwd.setError("");
                        til_again_pwd.setErrorEnabled(false);
                    }
                } else {
                    til_again_pwd.setError("");
                    til_again_pwd.setErrorEnabled(false);
                }


            }
        });

        edt_pwd.setOnFocusChangeListener(new View.
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
        edt_again_pwd.setOnFocusChangeListener(new View.
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
}
