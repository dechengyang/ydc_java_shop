package com.ydcjavashop.shop.base;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ydcjavashop.shop.base.mvp.BasePresenter;
import com.ydcjavashop.shop.base.mvp.IBaseView;
import com.ydcjavashop.shop.base.mvp.factory.PresenterMvpFactory;
import com.ydcjavashop.shop.base.mvp.factory.PresenterMvpFactoryImpl;
import com.ydcjavashop.shop.base.mvp.proxy.BaseMvpProxy;
import com.ydcjavashop.shop.base.mvp.proxy.PresenterProxyInterface;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.SwipeToLoadLayout;


/**
 * Created by decheng.yang on 2018/2/5.
 * 指定子类具体的View必须继承自IBaseView
 * 指定子类具体的Presenter必须继承自BasePresenter
 */

public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends Fragment implements PresenterProxyInterface<V, P> {
    protected View rootView;

    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    protected abstract void initTitle();
    protected abstract void initView();
    protected abstract void setData();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
        Log.d("Fragment","BaseFragment->onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment","BaseFragment->onCreateView");
        return super.onCreateView(inflater,container,savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.rootView=view;
        initTitle();
        initView();
        mProxy.onResume((V) this);
        setData();
    }

    @Override
    public void onResume() {
        super.onResume();
        //mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();

        Log.d("Fragment", "BaseFragment->onDestroy");
    }

    protected void onComplete(SwipeToLoadLayout refresh){
        if (refresh!=null){
            if (refresh.isRefreshing()){
                refresh.setRefreshing(false);
            }else if (refresh.isLoadingMore()){
                refresh.setLoadingMore(false);
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    /**
     * 可以实现自己PresenterMvpFactory工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }


    /**
     * 获取创建Presenter的工厂
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /**
     * 获取Presenter
     * @return 返回子类创建的Presenter
     */
    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }
}
