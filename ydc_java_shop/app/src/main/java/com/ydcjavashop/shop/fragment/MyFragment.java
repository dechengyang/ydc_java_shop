package com.ydcjavashop.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.AccountManageActivity;
import com.ydcjavashop.shop.base.BaseFragment;
import com.ydcjavashop.shop.base.Feed;
import com.ydcjavashop.shop.news.beans.NewsBean;
import com.ydcjavashop.shop.news.presenter.NewsPresenter;
import com.ydcjavashop.shop.news.view.INewsView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by decheng.yang on 2018/2/5.
 */

public class MyFragment extends BaseFragment<INewsView, NewsPresenter> implements INewsView {

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

    @OnClick({R.id.iv_portrait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_portrait:
                //PhotoDialogFragment.newInStance().setClicTakePhotookListener(MyFragment.this).show(getFragmentManager(), TAG);
                Intent intent = new Intent(getActivity(), AccountManageActivity.class);
                startActivity(intent);

                break;

        }
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
    public void succeed(Feed feed) {

    }


}
