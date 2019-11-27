package com.ydc.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ydc.base.R;
import com.ydc.base.util.DensityUtil;
import com.ydc.base.util.ResourceUtil;
import com.ydc.base.util.Tool;


/**
 * @Description 自定义头部导航栏
 * @Author ydc
 * @CreateDate 2019/11/27
 * @Version 1.0
 */
public class TitleView extends RelativeLayout {
    private RelativeLayout titleView;
    private RelativeLayout leftTitleView;
    private RelativeLayout rightTitleView;
    private ImageView mLeftImageBtn;
    private TextView mLeftTextBtn;
    private ImageView mRightImageBtn;
    private TextView mRightTextBtn;
    private TextView mTitle;
    private ImageView mtitleImg;
    public TextView closeText;
    private int titleSize;
    private int textSize;

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        titleView.setBackgroundColor(backgroundColor);
    }

    private int backgroundColor;

    /**
     * 获取右边按钮的view
     * @return
     */
    public RelativeLayout getRightTitleView() {
        return rightTitleView;
    }
    /**
     * 获取右边按钮的view
     * @return
     */
    public RelativeLayout getLeftTitleView() {
        return leftTitleView;
    }



    // 设置字体颜色
    public void setTColor(int textColor) {
        mTitle.setTextColor(textColor);
    }
    // 设置字体大小
    public void setTSize(int textSize) {
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);

    }
    public void setTitle(String text) {
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(text);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
    }

    public void setTitle(int stringID) {
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(stringID);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
    }

    public void setTitleImg(int resId) {
        mtitleImg.setVisibility(View.VISIBLE);
        mtitleImg.setImageResource(resId);
    }

    // 显示标题左侧
    public void showLeftButton(OnClickListener listener) {
        leftTitleView.setVisibility(View.VISIBLE);
        leftTitleView.setOnClickListener(listener);
    }

    // 隐藏标题左侧
    public void hiddenLeftButton() {
        leftTitleView.setVisibility(View.GONE);
        leftTitleView.setOnClickListener(null);
    }

    // 设置标题左侧图片内容
    public void setLeftImageButton(int imageID) {
        mLeftImageBtn.setVisibility(View.VISIBLE);
        mLeftImageBtn.setImageResource(imageID);
    }

    // 隐藏标题左侧图片
    public void hiddenLeftImageButton() {
        mLeftImageBtn.setVisibility(View.GONE);
    }

    // 设置标题左侧文字内容
    public void setLeftTextButton(String text) {
        mLeftTextBtn.setVisibility(View.VISIBLE);
        mLeftTextBtn.setText(text);
        mLeftTextBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    // 设置标题左侧文字内容
    public void setLeftTextButton(int stringID) {
        mLeftTextBtn.setVisibility(View.VISIBLE);
        mLeftTextBtn.setText(stringID);
        mLeftTextBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    // 隐藏标题左侧文字
    public void hiddenLeftTextButton() {
        mLeftTextBtn.setVisibility(View.GONE);
    }

    // 显示标题右侧
    public void showRightButton(OnClickListener listener) {
        rightTitleView.setVisibility(View.VISIBLE);
        rightTitleView.setOnClickListener(listener);
    }

    // 隐藏标题右侧
    public void hiddenRightButton() {
        rightTitleView.setVisibility(View.GONE);
        leftTitleView.setOnClickListener(null);
    }

    // 设置标题右侧图片内容
    public void setRightImageButton(int imageID) {
        mRightImageBtn.setVisibility(View.VISIBLE);
        mRightImageBtn.setImageResource(imageID);
    }

    // 隐藏标题右侧图片
    public void hiddenRightImageButton() {
        mRightImageBtn.setVisibility(View.GONE);
    }

    // 设置标题右侧文字内容
    public void setRightTextButton(String text) {
        mRightTextBtn.setVisibility(View.VISIBLE);
        mRightTextBtn.setText(text);
        mRightTextBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }
    // 设置标题右侧文字内容
    public void setRightTextCorlr(int color) {
        mRightTextBtn.setTextColor(color);
    }

    // 设置标题右侧文字内容
    public void setRightTextButton(int stringID) {
        mRightTextBtn.setVisibility(View.VISIBLE);
        mRightTextBtn.setText(stringID);
        mRightTextBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    // 隐藏标题右侧文字
    public void hiddenRightTextButton() {
        mRightTextBtn.setVisibility(View.GONE);
    }

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.title_view, this, true);
        initViews();
        initSize();
        hidenAll();
    }

    public void initViews() {
        titleView = (RelativeLayout) findViewById(R.id.
                title_view);
        leftTitleView = (RelativeLayout) findViewById(R.id.title_left);
        rightTitleView = (RelativeLayout) findViewById(R.id.title_right);
        mLeftImageBtn = (ImageView) findViewById(R.id.left_image_btn);
        mLeftTextBtn = (TextView) findViewById(R.id.left_text_btn);
        mRightImageBtn = (ImageView) findViewById(R.id.right_image_btn);
        mRightTextBtn = (TextView) findViewById(R.id.right_text_btn);
        mTitle = (TextView) findViewById(R.id.title_text);
        closeText = (TextView) findViewById(R.id.close_text);
        mtitleImg = (ImageView) findViewById(R.id.title_img);
//        setBackground();
    }
    public void setBackground(){
        titleView.setBackgroundColor(getBackgroundColor());
    }

    public void initSize() {

        int[] screenDispaly = Tool.getScreenDispaly(getContext());
        int with = screenDispaly[0];
        int height = screenDispaly[1];
//        int titleHeight = (int) (height * 0.08);
        int titleHeight = DensityUtil.dip2px(this.getContext(),44);
        LayoutParams titleViewParams = (LayoutParams) titleView
                .getLayoutParams();
        titleViewParams.height = titleHeight;
        titleView.setLayoutParams(titleViewParams);
        titleSize = ResourceUtil.getXmlDef(this.getContext(),
                R.dimen.size_20_sp);
        textSize = ResourceUtil.getXmlDef(this.getContext(),
                R.dimen.size_16_sp);
        Log.d("TitleView", "titleSize:" + titleSize);
    }

    public void setTitlePadding(int padding) {
        leftTitleView.setPadding(padding, 0, padding, 0);
        rightTitleView.setPadding(padding, 0, padding, 0);
    }

    public void hidenAll() {
        mTitle.setVisibility(View.GONE);
        leftTitleView.setVisibility(View.GONE);
        rightTitleView.setVisibility(View.GONE);
        mLeftImageBtn.setVisibility(View.GONE);
        mLeftTextBtn.setVisibility(View.GONE);
        mRightImageBtn.setVisibility(View.GONE);
        mRightTextBtn.setVisibility(View.GONE);
    }

    public void setCloseVisibility(int visibility, OnClickListener listener) {
        closeText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        closeText.setVisibility(visibility);
        closeText.setOnClickListener(listener);
    }

}
