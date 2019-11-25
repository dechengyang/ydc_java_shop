package com.ydcjavashop.shop.news.model;

import com.ydcjavashop.shop.interfaces.INewService;
import com.ydcjavashop.shop.news.beans.NewsRequestModel;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**ydc 新闻数据处理协议
 * Created by Administrator on 2017/7/6.
 */

public class NewsModel extends Model {
    private INewService service=createService(INewService.class);

    @Override
    public Observable<NewsRequestModel> loadNews(String url, int type) {
        Map<String, String> map = new HashMap<>();
        //map.put("type", type+"");
        return service.getNewList(url,map);
    }
}
