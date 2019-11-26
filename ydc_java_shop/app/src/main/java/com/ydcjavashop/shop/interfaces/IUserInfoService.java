package com.ydcjavashop.shop.interfaces;

import com.ydcjavashop.shop.account.bean.TokenBean;
import com.ydcjavashop.shop.base.Feed;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface IUserInfoService {
    //中文参数提交
    @Headers("Content-Type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST
    Observable<Feed<TokenBean>> login(@Url String url,
                                          @FieldMap Map<String, String> params);
}
