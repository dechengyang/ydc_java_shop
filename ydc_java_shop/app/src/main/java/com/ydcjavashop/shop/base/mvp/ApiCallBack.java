package com.ydcjavashop.shop.base.mvp;

import com.ydcjavashop.shop.base.BaseFeed;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @Description 处理网络数据处理完成后的回调响应（观察者）
 * @Author ydc
 * @CreateDate 2016/10/28
 * @Version 1.0
 */
public abstract class ApiCallBack<M> extends Subscriber<M> {
    /**
     * @description 成功接口回调，提供给View处理页面问题
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public abstract void onSuccess(M modelBean);

    /**
     * @description 失败接口回调，提供给View处理页面问题
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public abstract void onFailure(String errorMsg);

    /**
     * @description 请求结束，提供给View处理页面问题
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public abstract void onFinished();

    @Override
    public void onNext(M modelBean) {
        if(modelBean!=null){
            BaseFeed feed = (BaseFeed) modelBean;
            String status = feed.getStatus();
            status="S";//由于是演示所以手动赋值，正在开发中和服务器协商好
            if("S".equalsIgnoreCase(status)){
                onSuccess(modelBean);
            }else {
                onFailure(feed.getMessage());
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() {
        onFinished();
    }

    /**
     * @description 統一处理异常的回调
     * @author Andy.fang
     * @createDate
     * @version 1.0
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int exceptionCode = httpException.code();
            String msg = httpException.getMessage();
            if (exceptionCode == 401) {
                msg = "用户名密码错误，请重新登录！";
            }
            if (exceptionCode == 403 || exceptionCode == 404 || exceptionCode == 407 || exceptionCode == 408) {
                msg = "网络链接超时，请稍后再试！";
            }
            if (exceptionCode == 501 || exceptionCode == 502 || exceptionCode == 504) {
                msg = "服务器无响应，请稍后再试！";
            }
            onFailure(msg);
        } else {
            onFailure(e.getMessage());
        }
        onFinished();
    }
}
