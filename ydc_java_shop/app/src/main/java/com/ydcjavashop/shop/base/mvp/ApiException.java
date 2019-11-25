package com.ydcjavashop.shop.base.mvp;

/**
 * @Description API网络请求异常
 * @Author Andy.fang
 * @CreateDate 2016/11/11
 * @Version 1.0
 */
public class ApiException extends RuntimeException {
    private int mErrorCode;
    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }
}
