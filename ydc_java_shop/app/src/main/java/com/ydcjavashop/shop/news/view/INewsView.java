package com.ydcjavashop.shop.news.view;

import com.ydcjavashop.shop.base.mvp.IBaseView;
import com.ydcjavashop.shop.news.beans.NewsBean;

import java.util.List;



/**ydc 新闻列表所特有的方法定义
 * Created by Administrator on 2017/7/6.
 */

public interface INewsView extends IBaseView {
    void addNews(List<NewsBean> newsList);
}
