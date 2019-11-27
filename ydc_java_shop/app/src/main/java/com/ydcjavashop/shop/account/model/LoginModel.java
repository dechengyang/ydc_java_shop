package com.ydcjavashop.shop.account.model;

import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.interfaces.IUserInfoService;
import java.util.Map;
import rx.Observable;

/**
 * Created by decheng.yang on 2018/3/24.
 */

public class LoginModel extends LoginAbstractModel {
    private IUserInfoService service = createService(IUserInfoService.class);

    @Override
    public Observable<Feed<TokenBean>> login(String url, Map<String, String> params) {
        return service.login(url, params);
    }


}
