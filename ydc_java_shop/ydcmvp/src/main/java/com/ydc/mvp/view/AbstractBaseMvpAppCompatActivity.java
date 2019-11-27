package com.ydc.mvp.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ydc.mvp.factory.PresenterMvpFactory;
import com.ydc.mvp.factory.PresenterMvpFactoryImpl;
import com.ydc.mvp.presenter.BaseMvpPresenter;
import com.ydc.mvp.proxy.BaseMvpProxy;
import com.ydc.mvp.proxy.PresenterProxyInterface;


/**
 * @author ydc
 * @date 2017/11/20
 * @description
 */
public class AbstractBaseMvpAppCompatActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends AppCompatActivity implements PresenterProxyInterface<V,P> {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("perfect-mvp","V onCreate");
        Log.e("perfect-mvp","V onCreate mProxy = " + mProxy);
        Log.e("perfect-mvp","V onCreate this = " + this.hashCode());
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp","V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp","V onDestroy = " + isChangingConfigurations());
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp","V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Log.e("perfect-mvp","V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Log.e("perfect-mvp","V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp","V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }
}
