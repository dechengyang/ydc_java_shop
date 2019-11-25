package com.ydcjavashop.shop.base.mvp;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Description 抽象的公用Presenter
 * @Author ydc
 * @CreateDate 20170707
 * @Version 1.0
 */
public abstract class BasePresenter<T extends IBaseView> implements Ipresenter<T> {
    protected T mMvpView;//所有View
    protected SubscriptionList mSubscriptions;//rx注册中心
    protected DataRepository mDataCenter;//数据中心
    //protected abstract SubscriptionList createSubscriptionList();//引入darger后取缔

    /**
     * Presenter被创建后调用
     *
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
        Log.e("perfect-mvp","P onCreatePersenter = ");
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        Log.e("perfect-mvp","P onSaveInstanceState = ");
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        Log.e("perfect-mvp","P onDestroy = ");
    }

    /**
     * @description 获取V
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public T getMvpView() {
        return mMvpView;
    }

    /**
     * @description view绑定P的时候初始化
     * @author ydc
     * @createDate
     * @version 1.0
     */
    @Override
    public void attachView(T view) {
        this.mMvpView = view;
        this.mSubscriptions = new SubscriptionList();
        this.mDataCenter = DataRepository.getInstance();
    }

    /**
     * @description view失去绑定清除
     * @author ydc
     * @createDate
     * @version 1.0
     */
    @Override
    public void detachView() {
        unsubscribe();
        this.mMvpView = null;
        this.mSubscriptions = null;
        this.mDataCenter = null;
    }

    @Override
    public void unsubscribe(){
        if(mSubscriptions!=null){
            mSubscriptions.clear();
        }
    }

    /**
     * @description 当前的view（fragemnt&activity是否存在）
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * @description 是否viewb绑定过P
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    /**
     * @description p&v没有绑定的异常
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }

    /**
     * @description 统一添加订阅关联被观察者和观察者
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if( observable!=null && subscriber!=null ){
            if (mSubscriptions == null) {
                mSubscriptions = new SubscriptionList();
            }
            mSubscriptions.clear();
            mSubscriptions.add(observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber));
        }
    }

}
