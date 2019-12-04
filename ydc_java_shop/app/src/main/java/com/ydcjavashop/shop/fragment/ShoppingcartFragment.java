package com.ydcjavashop.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ydc.mvp.factory.CreatePresenter;
import com.ydc.mvp.view.AbstractBaseMvpFragment;
import com.ydc.networkservice.bean.BaseFeed;
import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnLoadMoreListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnRefreshListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.SwipeToLoadLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

@CreatePresenter(LoginPresenter.class)
public class ShoppingcartFragment extends AbstractBaseMvpFragment<ILoginMvpView, LoginPresenter> implements ILoginMvpView, OnRefreshListener, OnLoadMoreListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
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
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mAdapter = new NewsAdapter(getActivity().getApplicationContext(), mData);
//        mRecyclerView.setAdapter(mAdapter);
//
//        mLoadLayout.setOnRefreshListener(this);
//        mLoadLayout.setOnLoadMoreListener(this);

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
