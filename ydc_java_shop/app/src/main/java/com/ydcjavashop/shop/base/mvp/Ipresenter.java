package com.ydcjavashop.shop.base.mvp;

/**
 * @Description MVP的P层 指定绑定的View必须继承自IBaseView
 * @Author ydc
 * @CreateDate 2016/10/10
 * @Version 1.0
 */
public interface Ipresenter<T extends IBaseView> {

    /**
     * @description 关联P与V（绑定，VIEW销毁适合解绑）
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void attachView(T view);

    /**
     * @description 取消关联P与V（防止内存泄漏）
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void detachView();

    /**
     * @description RX订阅
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void subscribe();

    /**
     * @description RX取消订阅
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void unsubscribe();

}
