package com.ydcjavashop.shop.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ydcjavashop.shop.R;


/**
 * Created by decheng.yang on 2017/10/19.
 */

public class PhotoDialogFragment extends BaseDialogFragment {

    private TextView tv_take_photo, tv_check_photo, tv_cancel;

    public PhotoDialogFragment setClicTakePhotookListener(OnClicTakePhotookListener onClicTakePhotookListener) {
        this.onClicTakePhotookListener = onClicTakePhotookListener;
        return this;
    }

    public static PhotoDialogFragment newInStance() {

        PhotoDialogFragment dialogFragment = new PhotoDialogFragment();
        Bundle bundle = new Bundle();
        /*bundle.putBoolean(IS_ADD_TO_CART, isAddToCart);
        bundle.putString(GOODS_ID, goodsId);
        bundle.putSerializable(LAST_SELECTED_MAP, lastSelectedMap);
        bundle.putInt(BUY_NUM, buyNum);
        bundle.putString("type",type);
        bundle.putString("targetId",targetId);
        bundle.putString("groupsOrderId",groupsOrderId);*/
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            //isAddToCart = bundle.getBoolean(IS_ADD_TO_CART);

        }
    }


    @Override
    protected int setFragmentViewId() {
        return R.layout.dialog_take_photo;
    }

    @Override
    protected void initView(View rootView) {

        tv_take_photo = (TextView) rootView.findViewById(R.id.tv_take_photo);
        tv_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClicTakePhotookListener != null) {
                    dismiss();
                    onClicTakePhotookListener.takePhoto();
                }
            }
        });
        tv_check_photo = (TextView) rootView.findViewById(R.id.tv_check_photo);
        tv_check_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClicTakePhotookListener != null) {
                    dismiss();
                    onClicTakePhotookListener.checkPhoto();
                }
            }
        });
        tv_cancel = (TextView) rootView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClicTakePhotookListener != null) {
                    dismiss();
                    onClicTakePhotookListener.takeClose();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    private OnClicTakePhotookListener onClicTakePhotookListener = null;

    public interface OnClicTakePhotookListener {
        void takePhoto();

        void checkPhoto();

        void takeClose();
    }
}
