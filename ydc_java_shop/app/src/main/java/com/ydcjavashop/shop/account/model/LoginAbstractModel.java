package com.ydcjavashop.shop.account.model;

import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.base.BaseModel;
import com.ydcjavashop.shop.base.Feed;

import java.util.Map;


import rx.Observable;

/**
 * ydc 获取数据的逻辑模块协议，也可以是接口，提供给P调用，在callback中更新V
 * Created by Administrator on 2017/7/6.
 */

public abstract class LoginAbstractModel extends BaseModel {
    public abstract Observable<Feed<TokenBean>> login(String url, Map<String, String> params);
}
