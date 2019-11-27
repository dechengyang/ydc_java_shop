package com.ydc.mvp.proxy;


import com.ydc.mvp.factory.PresenterMvpFactory;
import com.ydc.mvp.presenter.BaseMvpPresenter;
import com.ydc.mvp.view.IBaseMvpView;

/**
 * @author ydc
 * @date 2017/11/20
 * @description 代理接口
 */
public interface PresenterProxyInterface<V extends IBaseMvpView,P extends BaseMvpPresenter<V>> {


    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
    PresenterMvpFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();


}
