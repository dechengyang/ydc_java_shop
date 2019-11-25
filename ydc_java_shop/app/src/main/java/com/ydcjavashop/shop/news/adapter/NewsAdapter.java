package com.ydcjavashop.shop.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.news.beans.NewsBean;
import com.ydcjavashop.shop.util.GlideHelper;
import com.ydcjavashop.shop.view.recyclerview.OnItemClick;
import com.ydcjavashop.shop.view.recyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description :
 * Author :
 * Email  :
 * Blog   :
 * Date   :
 */
public class NewsAdapter extends RecyclerAdapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<NewsBean> listBean = new ArrayList<>();

    public NewsAdapter(Context context, List<NewsBean> listBean) {
        this.mContext = context;
        this.listBean = listBean;
        this.notifyDataSetChanged();
    }

    public void addAll(List<NewsBean> listBean) {

        this.listBean.addAll(listBean);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.listBean.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsBean news = listBean.get(position);
        if (news == null) {
            return;
        }
        ((ViewHolder) holder).mTitle.setText(news.getTitle());
        ((ViewHolder) holder).mDesc.setText(news.getDigest());
        GlideHelper.showImage(mContext, news.getImgsrc(), ((ViewHolder) holder).ivNews);


    }


    @Override
    public int getItemCount() {
        return listBean == null ? 0 : listBean.size();
    }


    private OnItemClick onItemClickListener;

    public void setOnItemClickListener(OnItemClick onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivNews)
        ImageView ivNews;
        @Bind(R.id.tvTitle)
        TextView mTitle;
        @Bind(R.id.tvDesc)
        TextView mDesc;

        RelativeLayout ll_list_item;
        ImageView tvOutPrintCt;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
