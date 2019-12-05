package com.ydcjavashop.shop.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ydc.base.util.Tool;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.home.bean.RecommendSpecialEntity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydc on 2017/3/3.
 */

public class RecommendIndexAdapter extends RecyclerView.Adapter<RecommendIndexAdapter.ViewHolder> {


    private List<RecommendSpecialEntity> listBean = new ArrayList<>();
    private Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getHeaderView() {
        return mHeaderView;
    }
    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecommendIndexAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        if(mHeaderView != null && viewType == TYPE_HEADER) return new RecommendIndexAdapter.ViewHolder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_list_item, parent, false);
        return new RecommendIndexAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final RecommendIndexAdapter.ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        final int realPosition = getRealPosition(holder);
        Glide.with(context).load(listBean.get(realPosition).getImgUrl()).centerCrop()
                .placeholder(R.drawable.image_default_white_bg)
                .fitCenter()
                .error(R.drawable.image_default_white_bg)
                .into(holder.homeListBottomIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tool.isFastDoubleClick()) return;
                int types= Integer.valueOf(listBean.get(realPosition).getJumpType());
                switch (types){
                }
            }
        });
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }
    @Override
    public int getItemCount() {
        return mHeaderView == null ? listBean.size() : listBean.size() + 1;
    }
    public void addAll(List<RecommendSpecialEntity> listBean) {
        if(this.listBean!=null&&this.listBean.size()>0){
            this.listBean.clear();
        }
        this.listBean.addAll(listBean);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.listBean.clear();
        this.notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homeListBottomIv;
        public ViewHolder(View itemView) {
            super(itemView);
            homeListBottomIv= (ImageView) itemView.findViewById(R.id.home_list_bottom_iv);
        }
    }
}
