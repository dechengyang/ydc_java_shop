package com.ydc.mvp.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.ydc.base.view.TitleView;
import com.ydc.mvp.R;
import com.ydc.mvp.factory.PresenterMvpFactory;
import com.ydc.mvp.factory.PresenterMvpFactoryImpl;
import com.ydc.mvp.presenter.BaseMvpPresenter;
import com.ydc.mvp.proxy.BaseMvpProxy;
import com.ydc.mvp.proxy.PresenterProxyInterface;

/**
 * @author ydc
 * @date 2017/11/17
 * @description 继承自FragmentActivity的基类MvpActivity
 * 使用代理模式来代理Presenter的创建、销毁、绑定、解绑以及Presenter的状态保存,其实就是管理Presenter的生命周期
 */
public abstract class AbstractBaseMvpFragmentActivity<V extends IBaseMvpView, P extends BaseMvpPresenter<V>> extends FragmentActivity implements PresenterProxyInterface<V, P> {

    protected ProgressDialog dialog;
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
    private BaseMvpProxy<V,P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V,P>createFactory(getClass()));

    protected abstract void initTitle();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("perfect-mvp","V onCreate");
        Log.e("perfect-mvp","V onCreate mProxy = " + mProxy);
        Log.e("perfect-mvp","V onCreate this = " + this.hashCode());

        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }

        titleView = (TitleView) this.findViewById(R.id.title);
        initTitle();
        initView();
        initData();
        setData();
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
        Log.e("perfect-mvp","V onDestroy = " );
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
        //ImmersionBar.with(this).statusBarColor(R.color.global_background).autoStatusBarDarkModeEnable(true, 0.2f).init();
    }

//*******************华丽的分隔线***************

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onFinish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        onFinish();
        super.onBackPressed();
    }

    public void onFinish() {
        finish();
        overridePendingTransition(R.anim.stay_remain, R.anim.out_to_right);

        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideSoftInput(this.getCurrentFocus());
        return super.onTouchEvent(event);
    }

    public void hideSoftInput(View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    //***********************华丽的分隔线*************************
    private void startActivityAnim() {
        overridePendingTransition(R.anim.in_from_right, R.anim.stay_remain);
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        startActivityAnim();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        startActivityAnim();
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
