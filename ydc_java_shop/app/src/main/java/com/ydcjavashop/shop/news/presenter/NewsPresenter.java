package com.ydcjavashop.shop.news.presenter;


import com.ydcjavashop.shop.base.mvp.ApiCallBack;
import com.ydcjavashop.shop.news.beans.NewsRequestModel;
import com.ydcjavashop.shop.news.model.Model;
import com.ydcjavashop.shop.news.model.NewsModel;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewsPresenter extends Presenter {

    private Model mModel;
    public  NewsPresenter(){
        mModel=new NewsModel();
    }

    @Override
    public void loadNews(int type, int page) {
        addSubscription(mModel.loadNews("nc/article/headline/T1348647909107/0-20.html",0), new ApiCallBack<NewsRequestModel>() {

            @Override
            public void onStart() {
                getMvpView().showLoading();
            }

            @Override
            public void onSuccess(NewsRequestModel modelBean) {
                if(modelBean!=null){
                    getMvpView().addNews(modelBean.getT1348647909107());
                }

            }

            @Override
            public void onFailure(String errorMsg) {
               getMvpView().showErrorMsg("","");
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
