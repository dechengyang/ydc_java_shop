package com.ydcjavashop.shop.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Feed <T> implements Serializable {


    private static final long serialVersionUID = 1L;
    protected String strJson;
    protected String strXml;
    protected String status;
    protected String code;
    protected String solution;
    protected String message;
    protected List<SubError> suberrors;
    protected T data;
    protected String token;
    protected List<T> mList;
    protected int totalSize;

    public Feed()
    {
    }

    public Feed(String status, String message)
    {
        this.status = status;
        this.message = message;
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

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SubError> getSuberrors() {
        return this.suberrors;
    }

    public void setSuberrors(List<SubError> suberrors) {
        this.suberrors = suberrors;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public int getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
