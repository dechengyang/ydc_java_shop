package com.ydcjavashop.shop.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.ydcjavashop.shop.R;
import com.ydcjavashop.shop.util.DensityUtil;


public class ClearEditText extends EditText implements OnFocusChangeListener, TextWatcher {
    //图标的偏移位置
    private int x = 0;
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属于不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);

    }

    private void init(Context context) {
        //设置清除图标

        //为了解决api版本过高的 引用图片出错的原因
        if (Build.VERSION.SDK_INT >= 21) {
            mClearDrawable = ContextCompat.getDrawable(context, R.mipmap.et_clear);
        } else {
            mClearDrawable = ContextCompat.getDrawable(context, R.mipmap.et_clear);
        }
        //设置图标位置
        x = DensityUtil.dip2px(context, 10);
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicHeight(), mClearDrawable.getIntrinsicHeight());
        setCompoundDrawablePadding(x);
        //默认设置隐藏图标
        setClearIconVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 �?  EditText的宽�? - 图标到控件右边的间距 - 图标的宽�?  �?
     * EditText的宽�? - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有�?�虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - x - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - x - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // TODO Auto-generated method stub
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count,
                              int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

}
