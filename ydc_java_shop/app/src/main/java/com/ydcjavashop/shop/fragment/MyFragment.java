package com.ydcjavashop.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ydc.mvp.factory.CreatePresenter;
import com.ydc.mvp.view.AbstractBaseMvpFragment;
import com.ydc.networkservice.bean.BaseFeed;
import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.AccountManageActivity;
import com.ydcjavashop.shop.account.AccountSettingActivity;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnLoadMoreListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnRefreshListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by decheng.yang on 2018/2/5.
 */

@CreatePresenter(LoginPresenter.class)
public class MyFragment extends AbstractBaseMvpFragment<ILoginMvpView, LoginPresenter> implements ILoginMvpView, OnRefreshListener, OnLoadMoreListener {

    //private View view;
    @Bind(R.id.iv_portrait)
    ImageView iv_portrait;


    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    Intent intent;

    @OnClick({R.id.iv_portrait, R.id.tv_account_manager,R.id.tv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_portrait:
                //PhotoDialogFragment.newInStance().setClicTakePhotookListener(MyFragment.this).show(getFragmentManager(), TAG);
//                Intent intent = new Intent(getActivity(), AccountManageActivity.class);
//                startActivity(intent);

                break;
            case R.id.tv_account_manager:
                intent = new Intent(getActivity(), AccountManageActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_setting:
                intent = new Intent(getActivity(), AccountSettingActivity.class);
                startActivity(intent);
                break;


        }
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


    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
