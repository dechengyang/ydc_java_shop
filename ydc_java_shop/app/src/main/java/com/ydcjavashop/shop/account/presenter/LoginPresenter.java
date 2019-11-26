package com.ydcjavashop.shop.account.presenter;

import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.account.model.LoginAbstractModel;
import com.ydcjavashop.shop.account.model.LoginModel;
import com.ydcjavashop.shop.base.Feed;
import com.ydcjavashop.shop.base.mvp.ApiCallBack;

import java.util.Map;

/**
 * Created by decheng.yang on 2018/2/22.
 */

public class LoginPresenter extends Presenter {
    private LoginAbstractModel mModel;
    public LoginPresenter() {
        mModel=new LoginModel();
    }

    @Override
    public void login(String url,  Map<String, String> params) {

        addSubscription(mModel.login(url, params), new ApiCallBack<Feed<TokenBean>>() {

            @Override
            public void onStart() {
                getMvpView().showLoading();
            }

            @Override
            public void onSuccess(Feed<TokenBean> feed) {
                if (feed.getCode().equals("1000")) {
                    getMvpView().succeed(feed);
                } else {
                    getMvpView().showErrorMsg(feed.getMessage(), feed.getMessage());
                }

            }

            @Override
            public void onFailure(String errorMsg) {
                getMvpView().showErrorMsg(errorMsg, errorMsg);
            }

            @Override
            public void onFinished() {
                getMvpView().hideLoading();
            }
        });
    }

    @Override
    public void subscribe() {

    }

}
