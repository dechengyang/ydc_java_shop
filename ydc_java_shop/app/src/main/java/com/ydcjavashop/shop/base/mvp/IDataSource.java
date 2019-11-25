package com.ydcjavashop.shop.base.mvp;
import com.ydcjavashop.shop.base.Feed;

import java.util.List;

import rx.Observable;

/**
 * @Description 数据中心
 * @Author ydc
 * @CreateDate 2016/10/27
 * @Version 1.0
 */
public interface IDataSource {

    /**
     * @description 获取一组数据
     * @author ydc
     * @createDate
     * @version 1.0
     */
    Observable<List<Feed>> getFeeds();
    /**
     * @description RX获取被观察者
     * @author ydc
     * @createDate
     * @version 1.0
     */
    Observable<Feed> getFeed(Feed feed);

    /**
     * @description 数据无效时候，需要回调处理
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void onInvalidData();

    /**
     * @description 保存数据
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void saveData(String id);

    /**
     * @description 删除数据
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void delData(String id);

    /**
     * @description 删除所有数据
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void delAllData();

    /**
     * @description 刷新数据
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void refreshData();


}
