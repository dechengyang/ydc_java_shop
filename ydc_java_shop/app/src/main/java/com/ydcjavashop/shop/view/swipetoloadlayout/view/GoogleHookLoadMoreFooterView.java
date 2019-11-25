package com.ydcjavashop.shop.view.swipetoloadlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.core.view.ViewCompat;

import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.SwipeLoadMoreTrigger;
import com.ydcjavashop.shop.view.swipetoloadlayout.base.SwipeTrigger;


public class GoogleHookLoadMoreFooterView extends FrameLayout implements SwipeTrigger, SwipeLoadMoreTrigger {

    private GoogleCircleProgressView progressView;

    private int mTriggerOffset;
    private int mFinalOffset;

    public GoogleHookLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public GoogleHookLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleHookLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.load_more_trigger_offset_google);
        mFinalOffset = context.getResources().getDimensionPixelOffset(R.dimen.load_more_final_offset_google);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        progressView = (GoogleCircleProgressView) findViewById(R.id.googleProgress);
        progressView.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_red,
                R.color.google_yellow,
                R.color.google_green);
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onLoadMore() {
        progressView.start();
    }

    @Override
    public void onPrepare() {
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onSwipe(int y, boolean isComplete) {
        float alpha = -y / (float) mTriggerOffset;
        ViewCompat.setAlpha(progressView, alpha);
        if (!isComplete){
            progressView.setProgressRotation(-y * (1f)/ (float) mFinalOffset);
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void complete() {
        progressView.stop();
    }

    @Override
    public void onReset() {
        ViewCompat.setAlpha(progressView, 1f);
    }
}
