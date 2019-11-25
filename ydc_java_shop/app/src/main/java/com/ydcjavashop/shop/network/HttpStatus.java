package com.ydcjavashop.shop.network;

/**
 * @Description HTTP返回状态
 * @Author ydc
 * @CreateDate 2016/11/11
 * @Version 1.0
 */
public class HttpStatus {
    public String mHttpCode;
    public String mHttpMsg;

    public String getmHttpMsg() {
        return mHttpMsg;
    }

    public void setmHttpMsg(String mHttpMsg) {
        this.mHttpMsg = mHttpMsg;
    }

    public String getmHttpCode() {
        return mHttpCode;
    }

    public void setmHttpCode(String mHttpCode) {
        this.mHttpCode = mHttpCode;
    }
}

