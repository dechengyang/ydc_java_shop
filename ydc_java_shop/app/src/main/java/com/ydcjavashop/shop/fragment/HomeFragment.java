package com.ydcjavashop.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ydc.mvp.factory.CreatePresenter;
import com.ydc.mvp.view.AbstractBaseMvpFragment;
import com.ydc.networkservice.bean.BaseFeed;
import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnLoadMoreListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnRefreshListener;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by decheng.yang on 2018/2/5.
 */

@CreatePresenter(LoginPresenter.class)
public class HomeFragment extends AbstractBaseMvpFragment<ILoginMvpView, LoginPresenter> implements ILoginMvpView, OnRefreshListener, OnLoadMoreListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

       /* mPresenter = new NewsPresenter();
        mPresenter.attachView(this);
        mPresenter.subscribe();
*/

        Log.d("Fragment", "HomeFragment->onCreateView");
        return rootView;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

        initBanner();
    }

    private void initBanner(){
    }

    @Override
    protected void setData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.loadNews(0, 0);
        //getMvpPresenter().loadNews(0, 0);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unsubscribe();
        }*/

        ButterKnife.unbind(this);
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
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


}
