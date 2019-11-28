package com.ydc.base.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 自定义Button
 * 作用：通过监听EditText是否输入文字，来决定Button是否可点击
 * 使用：button.setEditText(new EditText[]{editText1, editText2});
 *
 * @author LZRUI
 */
public class CheckEditTextEmptyButton extends TextView implements TextWatcher {

    private TextView[] mTextViews;

    public CheckEditTextEmptyButton(Context context) {
        super(context);
    }

    public CheckEditTextEmptyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckEditTextEmptyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置监听的EditText
     *
     * @param ets
     */
    public void setEditText(TextView... ets) {
        setEnabled(false);
        if (ets != null && ets.length > 0) {
            mTextViews = ets;
            for (TextView textView : ets) {
                textView.addTextChangedListener(this);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mTextViews != null && mTextViews.length > 0) {
            /**
             * 通过循环判断所有的EditText是否都输入了文字
             */
            for (TextView textView : mTextViews) {
                if (textView.getText().toString().length() > 0) {
                    setEnabled(true);
                } else {
                    setEnabled(false);
                    break;
                }
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     *
     */
    public void AutoExecuteonTextChanged() {
        for (TextView textView : mTextViews) {
            if (textView.getText().toString().length() > 0) {
                setEnabled(true);
            } else {
                setEnabled(false);
                break;
            }
        }
    }
}
