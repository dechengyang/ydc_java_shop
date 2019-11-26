package com.ydcjavashop.shop.account.presenter;


import com.ydcjavashop.shop.account.view.ILoginView;
import com.ydcjavashop.shop.base.mvp.BasePresenter;

import java.util.Map;

/**ydc 新闻类的协议也可以是接口
 * Created by Administrator on 2017/7/6.
 */
abstract class Presenter extends BasePresenter<ILoginView> {
    public abstract void login(String url, Map<String, String> params);
}
