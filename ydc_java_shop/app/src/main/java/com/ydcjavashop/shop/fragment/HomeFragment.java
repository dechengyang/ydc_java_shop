package com.ydcjavashop.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ydc.base.util.DensityUtil;
import com.ydc.mvp.factory.CreatePresenter;
import com.ydc.mvp.view.AbstractBaseMvpFragment;
import com.ydc.networkservice.bean.BaseFeed;
import com.ydc.networkservice.bean.Feed;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.account.presenter.LoginPresenter;
import com.ydcjavashop.shop.account.view.ILoginMvpView;
import com.ydcjavashop.shop.home.adapter.NavAdapterViewHolder;
import com.ydcjavashop.shop.home.adapter.RecommendIndexAdapter;
import com.ydcjavashop.shop.home.bean.DataFactory;
import com.ydcjavashop.shop.home.bean.Nav;
import com.ydcjavashop.shop.home.bean.RecommendSpecialEntity;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnLoadMoreListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.OnRefreshListener;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.SwipeToLoadLayout;
import com.zaaach.transformerslayout.TransformersLayout;
import com.zaaach.transformerslayout.TransformersOptions;
import com.zaaach.transformerslayout.holder.Holder;
import com.zaaach.transformerslayout.holder.TransformersHolderCreator;
import com.zaaach.transformerslayout.listener.OnTransformersItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by decheng.yang on 2018/2/5.
 */

@CreatePresenter(LoginPresenter.class)
public class HomeFragment extends AbstractBaseMvpFragment<ILoginMvpView, LoginPresenter> implements ILoginMvpView, OnRefreshListener, OnLoadMoreListener {
    RecyclerView homeRecycler;
    SwipeToLoadLayout mRefresh;
    private TransformersLayout<Nav> transformersLayout;
    private RecommendIndexAdapter mAdapter;
    private View headView;
    private LinearLayout ll_content;
    private  List<RecommendSpecialEntity> mList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

       /* mPresenter = new NewsPresenter();
        mPresenter.attachView(this);
        mPresenter.subscribe();
*/

        Log.d("Fragment", "HomeFragment->onCreateView");
        return rootView;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {


        initBanner();
        homeRecycler=(RecyclerView)rootView.findViewById(R.id.swipe_target);
        mRefresh=(SwipeToLoadLayout)rootView.findViewById(R.id.refresh);
        mAdapter= new RecommendIndexAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        homeRecycler.setLayoutManager(manager);
        homeRecycler.setAdapter(mAdapter);
        mRefresh.setOnRefreshListener(this);
        mRefresh.setOnLoadMoreListener(this);
        mRefresh.setLoadMoreEnabled(false);
        setHeader(homeRecycler);
        headView=mAdapter.getHeaderView();
        ll_content=(LinearLayout) headView.findViewById(R.id.ll_content);
        transformersLayout=(TransformersLayout) headView.findViewById(R.id.transformers_layout);
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.home_list_header_layout, view, false);
        mAdapter.setHeaderView(header);
    }
    private void initBanner(){
    }

    @Override
    protected void setData() {
        final List<Nav> navList = DataFactory.loadData();
        //options可选配置
        TransformersOptions options = new TransformersOptions.Builder()
                .lines(2)
                .spanCount(5)
                .scrollBarWidth(DensityUtil.dip2px(getActivity(), 40))
                .scrollBarHeight(DensityUtil.dip2px(getActivity(), 3))
                .scrollBarRadius(DensityUtil.dip2px(getActivity(), 3) / 2f)
                .scrollBarTopMargin(DensityUtil.dip2px(getActivity(), 6))
                .build();
        transformersLayout.apply(options)
                .addOnTransformersItemClickListener(new OnTransformersItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "点击：" + navList.get(position).getText() + "，位置" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .load(navList, new TransformersHolderCreator<Nav>() {
                    @Override
                    public Holder<Nav> createHolder(View itemView) {
                        return new NavAdapterViewHolder(itemView);
                    }
                    @Override
                    public int getLayoutId() {
                        return R.layout.item_nav_list;
                    }
                });

        for (int i=0;i<15;i++){
            RecommendSpecialEntity m=new RecommendSpecialEntity();
            if(i==2){
                m.setImgUrl("https://img.alicdn.com/tps/TB1XF.gJpXXXXaUXVXXXXXXXXXX-570-400.jpg");
            }else {
                m.setImgUrl("https://img.alicdn.com/tps/TB1oHwXMVXXXXXnXVXXXXXXXXXX-570-400.jpg");
            }
            mList.add(m);

        }
        mAdapter.addAll(mList);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.loadNews(0, 0);
        //getMvpPresenter().loadNews(0, 0);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unsubscribe();
        }*/

        ButterKnife.unbind(this);
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
    public void succeed(BaseFeed feed) {

    }

    @Override
    public void responseSucceed(Feed feed) {

    }



    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


}
