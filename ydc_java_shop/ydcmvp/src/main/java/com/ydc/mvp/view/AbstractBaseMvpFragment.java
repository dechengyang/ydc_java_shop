package com.ydc.mvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ydc.base.view.TitleView;
import com.ydc.mvp.R;
import com.ydc.mvp.factory.PresenterMvpFactory;
import com.ydc.mvp.factory.PresenterMvpFactoryImpl;
import com.ydc.mvp.presenter.BaseMvpPresenter;
import com.ydc.mvp.proxy.BaseMvpProxy;
import com.ydc.mvp.proxy.PresenterProxyInterface;


/**
 * @author ydc
 * @date 2017/11/20
 * @description 继承Fragment的MvpFragment基类
 */
public abstract class AbstractBaseMvpFragment<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends Fragment implements PresenterProxyInterface<V, P> {

    protected ProgressDialog dialog;
    //title
    protected TitleView titleView;
    protected View rootView;
    protected AbstractBaseMvpFragmentActivity mActivity;

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
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState);
        }
        mActivity= (AbstractBaseMvpFragmentActivity) getActivity();
        Log.d("Fragment", "BaseFragment->onCreate");
        //setStatusBar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Fragment", "BaseFragment->onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.rootView = view;
        if (mProxy != null) {
            mProxy.onResume((V) this);
        }
        initTitle();
        initView();
        setData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
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
     * @return P
     */
    @Override
    public P getMvpPresenter() {
        return mProxy.getMvpPresenter();
    }


    /**
     * Created by yinsujun on 2016/4/1 0001 13:33
     * 设置标题及返回键
     * title:标题名称
     * id：title
     */
    protected void setTitle(String title) {
        titleView = (TitleView) rootView.findViewById(R.id.title);
        titleView.setTitle(title);
    }


    protected void setStatusBar2() {
        //StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
        //StatusBarFontHelper.setStatusBarMode(this, true);
        //StatusBarUtil.setColor(BaseActivity.this,getResources().getColor(R.color.white), 0);

        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }

    }
    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        //ImmersionBar.with(this).statusBarColor(R.color.global_background).init();
        //ImmersionBar.with(this).statusBarColor(R.color.tatusbarcolor_black_56).autoStatusBarDarkModeEnable(true, 0.2f).init();
    }

    protected ProgressDialog initProgressDialog(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        return dialog;
    }


}
