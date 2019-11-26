package com.ydcjavashop.shop.base;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2017/7/6.
 * 顶级类返回类型
 */

public class Feed<T> extends BaseFeed implements Serializable {


    private static final long serialVersionUID = 1L;
    protected String strJson;
    protected String strXml;
    protected List<SubError> suberrors;
    protected T data;
    protected List<T> mList;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    protected T result;

    public Feed() {
    }

    public Feed(String status, String message) {
        //this.status = status;
        //this.message = message;
    }

    public String getStrJson() {
        return this.strJson;
    }

    public void setStrJson(String strJson) {
        this.strJson = strJson;
    }

    public String getStrXml() {
        return this.strXml;
    }

    public void setStrXml(String strXml) {
        this.strXml = strXml;
    }


    public List<SubError> getSuberrors() {
        return this.suberrors;
    }

    public void setSuberrors(List<SubError> suberrors) {
        this.suberrors = suberrors;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getmList() {
        return this.mList;
    }

    public void setmList(List<T> mList) {
        this.mList = mList;
    }
}
