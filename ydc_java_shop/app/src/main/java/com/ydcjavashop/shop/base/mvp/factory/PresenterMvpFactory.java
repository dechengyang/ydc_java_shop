package com.ydcjavashop.shop.base.mvp.factory;


import com.ydcjavashop.shop.base.mvp.BasePresenter;
import com.ydcjavashop.shop.base.mvp.IBaseView;

/**
 * @author ydc
 * @date 2017/11/17
 * @description Presenter工厂接口
 */
public interface PresenterMvpFactory<V extends IBaseView,P extends BasePresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
