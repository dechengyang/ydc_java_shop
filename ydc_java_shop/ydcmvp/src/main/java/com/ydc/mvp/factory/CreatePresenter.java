package com.ydc.mvp.factory;

import com.ydc.mvp.presenter.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



/**
 * @author ydc
 * @date 2017/11/17
 * @description 标注创建Presenter的注解
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseMvpPresenter> value();
}
