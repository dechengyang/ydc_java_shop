package com.ydcjavashop.shop.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ydc.mvp.view.AbstractBaseMvpFragmentActivity;
import com.ydc.networkservice.bean.BaseFeed;
import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.MainActivity;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;


/**
 * Created by decheng.yang on 2018/2/22.
 */

public class WelcomeActivity extends AbstractBaseMvpFragmentActivity<ILoginMvpView, LoginPresenter> implements ILoginMvpView {
    private Handler handler;
    private Runnable jumpRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    @Override
    protected void onStart() {
        super.onStart();
        intentUi(2000);
    }

    @Override
    protected void initTitle() {

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


    private void intentUi(long delayMillis) {
        jumpRunnable = new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        handler = new Handler();
        handler.postDelayed(jumpRunnable, delayMillis);
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
    public void succeed(BaseFeed feed) {

    }

    @Override
    public void responseSucceed(Feed feed) {

    }


}
