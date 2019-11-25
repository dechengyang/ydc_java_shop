package com.ydcjavashop.shop.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.base.BaseFragment;
import com.ydcjavashop.shop.base.mvp.factory.CreatePresenter;
import com.ydcjavashop.shop.news.beans.NewsBean;
import com.ydcjavashop.shop.news.presenter.NewsPresenter;
import com.ydcjavashop.shop.news.view.INewsView;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnLoadMoreListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnRefreshListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by decheng.yang on 2018/2/5.
 */

@CreatePresenter(NewsPresenter.class)
public class HomeFragment extends BaseFragment<INewsView, NewsPresenter> implements INewsView, OnRefreshListener, OnLoadMoreListener {



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
        //mPresenter.loadNews(0, 0);
        getMvpPresenter().loadNews(0, 0);

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
    public void addNews(List<NewsBean> newsList) {

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
    public void onRefresh() {
        getMvpPresenter().loadNews(0, 0);
    }

    @Override
    public void onLoadMore() {
        getMvpPresenter().loadNews(0, 0);
    }


}
