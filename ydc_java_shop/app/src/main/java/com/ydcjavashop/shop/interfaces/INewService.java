package com.ydcjavashop.shop.interfaces;
import com.ydcjavashop.shop.news.beans.NewsRequestModel;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**网络接口
 * Created by Administrator on 2017/7/6.
 */

public interface INewService {

    @GET
    Observable<NewsRequestModel> getNewList(@Url String url,
                                            @QueryMap Map<String, String> params);
}
