package com.ydcjavashop.shop.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.base.mvp.BasePresenter;
import com.ydcjavashop.shop.base.mvp.IBaseView;
import com.ydcjavashop.shop.base.mvp.factory.PresenterMvpFactory;
import com.ydcjavashop.shop.base.mvp.factory.PresenterMvpFactoryImpl;
import com.ydcjavashop.shop.base.mvp.proxy.BaseMvpProxy;
import com.ydcjavashop.shop.base.mvp.proxy.PresenterProxyInterface;
import com.ydcjavashop.shop.view.TitleView;


/**
 * Created by Administrator on 2017/7/8.
 */

public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends FragmentActivity implements PresenterProxyInterface<V, P> {

    //对外提供一个title的方法
    protected TitleView titleView;
    protected Context context;
    protected Activity activity;
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
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        activity=this;
        context = this;
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }


        titleView = (TitleView) this.findViewById(R.id.title);
        initTitle();
        //initView();
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
        if (mProxy != null) {
            mProxy.onResume((V) this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = " + isChangingConfigurations());
        if (mProxy != null) {
            mProxy.onDestroy();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Log.e("perfect-mvp", "V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Log.e("perfect-mvp", "V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp", "V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }







    protected void setTitleBackground(String title, int leftImageId) {
        TitleView titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTColor(ContextCompat.getColor(context, R.color.white));
        titleView.setBackgroundColor(ContextCompat.getColor(context, R.color.staging_theme_color));
        titleView.setTitle(title);
        titleView.setLeftImageButton(leftImageId);
        titleView.showLeftButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setRightTitleBackground(String title, String rightMessage, int leftImageId, View.OnClickListener clickListener) {
        TitleView titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTColor(ContextCompat.getColor(context, R.color.white));
        titleView.setBackgroundColor(ContextCompat.getColor(context, R.color.staging_theme_color));
        titleView.setRightTextButton(rightMessage);
        titleView.setRightTextCorlr(ContextCompat.getColor(context, R.color.white));
        titleView.showRightButton(clickListener);
        titleView.setTitle(title);
        titleView.setLeftImageButton(leftImageId);
        titleView.showLeftButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Created by yinsujun on 2016/4/1 0001 13:33
     * 设置标题及返回键
     * title:标题名称
     * id：title
     */
    protected void setTitle(String title) {
        TitleView titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTitle(title);

        titleView.setLeftImageButton(R.mipmap.back);
        titleView.showLeftButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setTitle(String title, int rightRid, View.OnClickListener onClickListener) {
        titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTitle(title);
        titleView.setLeftImageButton(R.mipmap.back);
        titleView.showLeftButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.setRightImageButton(rightRid);
        titleView.showRightButton(onClickListener);
    }

    /**
     * Created by yinsujun on 2016/4/1 0001 13:33
     * 设置标题 自定义按钮监听
     * title:标题名称
     * id：title
     */
    protected void setTitle(String title, View.OnClickListener clickListener) {
        titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTitle(title);
        titleView.setLeftImageButton(R.mipmap.back);
        titleView.showLeftButton(clickListener);
    }

    /**
     * Created by yinsujun on 2016/4/1 0001 13:33
     * 设置标题,左侧描述及返回键
     * title:标题名称
     * id：title
     */
    protected void setTitle(String title, String leftMessage) {
        TitleView titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTitle(title);
        titleView.setLeftTextButton(leftMessage);
        titleView.setLeftImageButton(R.mipmap.back);
        titleView.showLeftButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setTitle(String title, String leftMessage, String rightMessage, int rightImageId, View.OnClickListener clickListener) {
        titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTitle(title);
        titleView.setLeftTextButton(leftMessage);
        titleView.setRightTextButton(rightMessage);
        if (rightImageId != 0) {
            titleView.setRightImageButton(rightImageId);
        }
        titleView.setLeftImageButton(R.mipmap.back);
        titleView.showLeftButton(clickListener);
        titleView.showRightButton(clickListener);
    }

    protected void setRightTitle(String title, String rightMessage, View.OnClickListener rightOnClick) {
        TitleView titleView = (TitleView) this.findViewById(R.id.title);
        titleView.setTitle(title);
        titleView.setRightTextButton(rightMessage);
        titleView.setLeftImageButton(R.mipmap.back);
        titleView.showLeftButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleView.showRightButton(rightOnClick);
    }
}
