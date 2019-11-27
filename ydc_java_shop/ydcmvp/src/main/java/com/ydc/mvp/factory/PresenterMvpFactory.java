package com.ydc.mvp.factory;


import com.ydc.mvp.presenter.BaseMvpPresenter;
import com.ydc.mvp.view.IBaseMvpView;

/**
 * @author ydc
 * @date 2017/11/17
 * @description Presenter工厂接口
 */
public interface PresenterMvpFactory<V extends IBaseMvpView,P extends BaseMvpPresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
