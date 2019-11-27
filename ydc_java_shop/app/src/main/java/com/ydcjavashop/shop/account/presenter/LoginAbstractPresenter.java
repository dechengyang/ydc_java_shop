package com.ydcjavashop.shop.account.presenter;


import com.ydc.mvp.presenter.BaseMvpPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;

import java.util.Map;

/**ydc 新闻类的协议也可以是接口
 * Created by Administrator on 2017/7/6.
 */
abstract class LoginAbstractPresenter extends BaseMvpPresenter<ILoginMvpView> {
    public abstract void login(String url, Map<String, String> params);
}
