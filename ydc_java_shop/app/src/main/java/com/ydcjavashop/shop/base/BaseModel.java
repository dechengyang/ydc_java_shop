package com.ydcjavashop.shop.base;

import com.ydcjavashop.shop.base.mvp.Imodel;
import com.ydcjavashop.shop.network.RxService;

/**
 * @Description 数据模型基础类
 * @Author ydc
 * @CreateDate 2016/11/2
 * @Version 1.0
 */
public abstract class BaseModel implements Imodel {

    /**
     * @description 返回服务接口对象实例
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> T createService(final Class<T> clazz) {
        validateServiceInterface(clazz);
        return (T) RxService.RETROFIT.createRetrofit().create(clazz);
    }

    /**
     * @description 校验接口合法性
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> void validateServiceInterface(Class<T> service) {
        if (service == null) {
            //AppToast.ShowToast("服务接口不能为空！");
        }
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

}
