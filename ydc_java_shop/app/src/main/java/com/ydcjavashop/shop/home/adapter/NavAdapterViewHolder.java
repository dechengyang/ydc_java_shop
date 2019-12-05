package com.ydcjavashop.shop.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ydc.base.util.GlideHelper;
import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.home.bean.Nav;
import com.zaaach.transformerslayout.holder.Holder;

public class NavAdapterViewHolder extends Holder<Nav> {
    private ImageView icon;
    private TextView text;

    public  NavAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        icon = itemView.findViewById(R.id.iv_menu_icon);
        text = itemView.findViewById(R.id.tv_menu_text);
    }

    @Override
    public void bindData(Context context, Nav data) {
        text.setText(data.getText());
//        icon.setImageResource(data.getIcon());
        GlideHelper.showImage(context,data.getUrl(),icon);
    }
}
