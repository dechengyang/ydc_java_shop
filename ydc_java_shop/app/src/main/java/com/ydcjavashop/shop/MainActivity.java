package com.ydcjavashop.shop;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ydc.config.ApiConfig;
import com.ydc.config.Constant;
import com.ydc.config.SharePreferenceKey;
import com.ydc.datarepository.sphelper.SharedPreferencesHelper;
import com.ydcjavashop.shop.account.LoginActivity;
import com.ydcjavashop.shop.base.BaseActivity;
import com.ydcjavashop.shop.base.BaseFragment;
import com.ydcjavashop.shop.base.Feed;
import com.ydcjavashop.shop.base.mvp.IBaseView;
import com.ydcjavashop.shop.fragment.CategoryFragment;
import com.ydcjavashop.shop.fragment.MyFragment;
import com.ydcjavashop.shop.news.HomeFragment;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yicheng.android.ui.materialdesignlibrary.views.LayoutRipple;

public class MainActivity extends BaseActivity implements IBaseView {
    @Bind(R.id.tab_home)
    LayoutRipple tab_home;
    @Bind(R.id.tab_home_icon)
    ImageView tabHomeIcon;
    @Bind(R.id.tab_home_text)
    TextView tabHomeText;

    @Bind(R.id.tab_calendar)
    LayoutRipple tab_calendar;
    @Bind(R.id.tab_calendar_icon)
    ImageView tabCalendarIcon;
    @Bind(R.id.tab_calendar_text)
    TextView tabCalendarText;

    @Bind(R.id.tab_shoppingcart)
    LayoutRipple tab_shoppingcart;
    @Bind(R.id.tab_shoppingcart_icon)
    ImageView tab_shoppingcart_icon;
    @Bind(R.id.tab_shoppingcart_text)
    TextView tab_shoppingcart_text;

    @Bind(R.id.tab_my)
    LayoutRipple tab_my;
    @Bind(R.id.tab_my_icon)
    ImageView tabMyIcon;
    @Bind(R.id.tab_my_text)
    TextView tabMyText;

    protected Context context;

    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private BaseFragment shoppingCartFragment;
    private BaseFragment myFragment;

    //Fragment管理器 获取Fragment的实例
    private FragmentManager fragmentManager;
    //Fragment事物   进行添加,移除,替换,以及执行其他动作。
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        fragmentManager = getSupportFragmentManager();
        switchTab(0);

        String token= (String) SharedPreferencesHelper.getInstance(MainActivity.this).get(SharePreferenceKey.TOKEN,"");
        String t=token;

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setData() {

    }

    //初始化当前的tab
    private void initTab() {
        tabHomeIcon.setImageResource(R.mipmap.home_normal_icon);
        tabCalendarIcon.setImageResource(R.mipmap.category_normal_icon);
        tab_shoppingcart_icon.setImageResource(R.mipmap.shoppingcart_normal_icon);
        tabMyIcon.setImageResource(R.mipmap.my_normal_icon);

        tabHomeText.setTextColor(ContextCompat.getColor(context, R.color.tab_text));
        tabCalendarText.setTextColor(ContextCompat.getColor(context, R.color.tab_text));

        tab_shoppingcart_text.setTextColor(ContextCompat.getColor(context, R.color.tab_text));
        tabMyText.setTextColor(ContextCompat.getColor(context, R.color.tab_text));
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (categoryFragment != null) {
            transaction.hide(categoryFragment);
        }

        if (shoppingCartFragment != null) {
            transaction.hide(shoppingCartFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    public void switchTab(int index) {
        initTab();
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                tabHomeIcon.setImageResource(R.mipmap.home_selected_icon);
                tabHomeText.setTextColor(ContextCompat.getColor(context, R.color.magenta));
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                    transaction.add(R.id.container, categoryFragment);
                } else {
                    transaction.show(categoryFragment);
                }
                tabCalendarIcon.setImageResource(R.mipmap.category_selected_icon);
                tabCalendarText.setTextColor(ContextCompat.getColor(context, R.color.magenta));
                break;
            case 2:
                if (shoppingCartFragment == null) {
                    shoppingCartFragment = new MyFragment();
                    ;
                    transaction.add(R.id.container, shoppingCartFragment);
                } else {
                    // 如果indexFragment不为空，则直接将它显示出来
                    transaction.show(shoppingCartFragment);
                }
                tab_shoppingcart_icon.setImageResource(R.mipmap.shoppingcart_selected_icon);
                tab_shoppingcart_text.setTextColor(ContextCompat.getColor(context, R.color.magenta));
                break;

            case 3:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.container, myFragment);
                } else {
                    // 如果indexFragment不为空，则直接将它显示出来
                    transaction.show(myFragment);
                }
                tabMyIcon.setImageResource(R.mipmap.my_selected_icon);
                tabMyText.setTextColor(ContextCompat.getColor(context, R.color.magenta));
                break;
        }

        transaction.commit();
    }

    @OnClick({R.id.tab_home, R.id.tab_calendar, R.id.tab_shoppingcart, R.id.tab_my})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tab_home:
                //Toast.makeText(this, "ask_layout", Toast.LENGTH_SHORT).show();
                switchTab(0);
                break;
            case R.id.tab_calendar:
                switchTab(1);

                break;
            case R.id.tab_shoppingcart:
                switchTab(2);

                break;
            case R.id.tab_my:
                switchTab(3);

                break;

        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(String msg, int progress) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showErrorMsg(String msg, String content) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void succeed(Feed feed) {

    }

}
