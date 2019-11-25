package com.ydcjavashop.shop.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.ydcjavashop.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提示Dialog
 *
 * @author LZRUI
 */
public class AlertDialogFragment extends DialogFragment {

    public static String TAG = "AlertDialogFragment";
    private LeftClickCallBack mLeftCallBack;
    private RightClickCallBack mRightCallBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_content)
    TextView tvContent;
    private View rootView;
    private Context context;

    public void setLeftCallBack(LeftClickCallBack mLeftCallBack) {
        this.mLeftCallBack = mLeftCallBack;
    }

    public void setRightCallBack(RightClickCallBack mRightCallBack) {
        this.mRightCallBack = mRightCallBack;
    }

    private static AlertDialogFragment newInstance(String title, String content, int contentLayoutId, String leftBtnText, String rightBtnText) {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        bundle.putInt("contentLayoutId", contentLayoutId);
        bundle.putString("leftBtnText", leftBtnText);
        bundle.putString("rightBtnText", rightBtnText);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 非中断保存
        if (getParentFragment() == null) {
            setRetainInstance(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.alert_dialog_layout, container);
        }
        ButterKnife.bind(this, rootView);
        initData(getArguments());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 这个判断很重要
        if (getDialog() == null) {
            setShowsDialog(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //  设置dialogFragment全屏显示，并且添加显示动画
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Window dialogWindow = getDialog().getWindow();
        dialogWindow.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.alert_dialog_bg));
        dialogWindow.setLayout((int) (dm.widthPixels * 0.78), getDialog().getWindow().getAttributes().height);
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.alert_dialog_anim_style);
    }

    private void initData(Bundle bundle) {
        if (bundle != null) {
            String title = bundle.getString("title");
            String content = bundle.getString("content");
            String leftBtnText = bundle.getString("leftBtnText");
            String rightBtnText = bundle.getString("rightBtnText");
            int contentLayoutId = bundle.getInt("contentLayoutId", 0);

            if (!TextUtils.isEmpty(title)) {
                tvTitle.setText(title);
                tvTitle.setVisibility(View.VISIBLE);
            }
            /**
             * 给按钮设置了文本内容则显示按钮
             */
            if (!TextUtils.isEmpty(leftBtnText)) {
                tvLeft.setVisibility(View.VISIBLE);
                tvLeft.setText(leftBtnText);
            } else {
                tvLeft.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(rightBtnText)) {
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText(rightBtnText);
            } else {
                tvRight.setVisibility(View.GONE);
            }

            /**
             * 如果content有内容，则直接显示内容。否则判断contentLayoutId是否有内容，如有则显示contentLayoutId里面的内容
             */
            if (!TextUtils.isEmpty(content)) {
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText(content);
            } else if (contentLayoutId != 0) {
                tvContent.setVisibility(View.GONE);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(contentLayoutId, null);
                llContent.addView(view);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            // 左边按钮
            case R.id.tv_left:
                dismiss();
                if (mLeftCallBack != null) {
                    mLeftCallBack.dialogLeftBtnClick();
                }
                break;
            // 右边按钮
            case R.id.tv_right:
                if (mRightCallBack != null) {
                    mRightCallBack.dialogRightBtnClick();
                }
                dismiss();
                break;
        }
    }

    public void hintLeftButton() {
        tvLeft.setVisibility(View.GONE);
    }

    /**
     * 左边按钮点击回调
     */
    public interface LeftClickCallBack {
        void dialogLeftBtnClick();
    }

    /**
     * 右边按钮点击回调
     */
    public interface RightClickCallBack {
        void dialogRightBtnClick();
    }

    public static class Builder {

        private String title;
        private String content;
        private int contentLayoutId;
        private String leftBtnText;
        private String rightBtnText;
        private LeftClickCallBack leftCallBack;
        private RightClickCallBack rightCallBack;

        public Builder() {

        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentText(String content) {
            this.content = content;
            return this;
        }

        public Builder setContentLayoutId(int contentLayoutId) {
            this.contentLayoutId = contentLayoutId;
            return this;
        }

        public Builder setLeftBtnText(String leftBtnText) {
            this.leftBtnText = leftBtnText;
            return this;
        }

        public Builder setRightBtnText(String rightBtnText) {
            this.rightBtnText = rightBtnText;
            return this;
        }

        public Builder setLeftClickCallBack(LeftClickCallBack callBack) {
            this.leftCallBack = callBack;
            return this;
        }

        public Builder setRightClickCallBack(RightClickCallBack callBack) {
            this.rightCallBack = callBack;
            return this;
        }

        public AlertDialogFragment build() {
            AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance(title, content, contentLayoutId, leftBtnText, rightBtnText);
            dialogFragment.setLeftCallBack(leftCallBack);
            dialogFragment.setRightCallBack(rightCallBack);
            return dialogFragment;
        }

    }

}
