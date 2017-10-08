package com.smile.titlebar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smile.titlebar.utils.ZQTools;

/**
 * Created by Smile on 2017/9/30.
 */

public class ZQTitleView extends RelativeLayout {

    private Context context;

    private TextView tvTitle;
    private TextView btnBack;
    private ImageButton ibtnBack;
    private TextView btnRight;
    private ImageButton ibtnRight;

    private String titleText = "";
    private int titleTextColor;
    private float titltTextSize = 16;

    private String backBtnText = "返回";
    private int backBtnTextColor = Color.parseColor("#fff000");
    private float backBtnTextSize = 14;

    private Drawable ibtnBackIcon;

    private String rightBtnText = "添加";
    private int rightBtnTextColor = Color.parseColor("#fff000");
    private float rightBtnTextSize = 14;

    private Drawable rightBackIcon;

    private OnTextBtnClickListener onTextBtnClickListener = null;
    private OnIconBackBtnClickListener onIconBackBtnClickListener = null;
    private OnRightTextBtnClickListener onRightTextBtnClickListener = null;
    private OnIconRightBtnClickListener onIconRightBtnClickListener = null;

    private ZQTitleView(Context context) {
        super(context);
    }

    public ZQTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
        initViews();
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZQTitleView);
        titleText = typedArray.getString(R.styleable.ZQTitleView_titleText);
        titleTextColor = typedArray.getColor(R.styleable.ZQTitleView_titleColor, Color.WHITE);
        titltTextSize = typedArray.getDimension(R.styleable.ZQTitleView_titltTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        backBtnText = typedArray.getString(R.styleable.ZQTitleView_backBtnText);
        backBtnTextColor = typedArray.getColor(R.styleable.ZQTitleView_backBtnTextColor, Color.WHITE);
        backBtnTextSize = typedArray.getDimension(R.styleable.ZQTitleView_backBtnTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        ibtnBackIcon = typedArray.getDrawable(R.styleable.ZQTitleView_ibtnBackIcon);

        rightBtnText = typedArray.getString(R.styleable.ZQTitleView_rightBtnText);
        rightBtnTextColor = typedArray.getColor(R.styleable.ZQTitleView_rightBtnTextColor, Color.WHITE);
        rightBtnTextSize = typedArray.getDimension(R.styleable.ZQTitleView_rightBtnTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

        rightBackIcon= typedArray.getDrawable(R.styleable.ZQTitleView_rightBackIcon);

        typedArray.recycle();
    }

    private void initViews() {
        addTitleTextView();
        if (backBtnText != null && backBtnText.length() > 0) {
            addLeftTextBtn();
        } else {
            addLeftIbtnBack();
        }

        if (rightBtnText != null && rightBtnText.length() > 0) {
            addRightTextBtn();
        } else {
            addRightIbtn();
        }
    }

    //标题
    private void addTitleTextView() {
        tvTitle = new TextView(context);
        tvTitle.setText(titleText);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titltTextSize);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvTitle, leftParams);
    }

    //左侧文字返回按钮
    private void addLeftTextBtn() {
        btnBack = new TextView(context);
        btnBack.setText(backBtnText);
        btnBack.setTextColor(backBtnTextColor);
        btnBack.setTextSize(TypedValue.COMPLEX_UNIT_PX, backBtnTextSize);
        btnBack.setPadding(ZQTools.dp2px(context, 14), 0, ZQTools.dp2px(context, 14), 0);
        btnBack.setGravity(Gravity.CENTER_VERTICAL);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(btnBack, leftParams);

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onTextBtnClickListener) {
                    onTextBtnClickListener.onClick(view);
                } else {
                    ((Activity) context).finish();
                }
            }
        });
    }

    //左侧返回图片按钮
    private void addLeftIbtnBack() {
        ibtnBack = new ImageButton(context);
        ibtnBack.setPadding(ZQTools.dp2px(context, 14), 0, ZQTools.dp2px(context, 14), 0);
        ibtnBack.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ibtnBack.setImageDrawable(ibtnBackIcon);
        ibtnBack.setBackground(null);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(ibtnBack, leftParams);

        ibtnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onIconBackBtnClickListener) {
                    onIconBackBtnClickListener.onClick(view);
                } else {
                    ((Activity) context).finish();
                }
            }
        });
    }

    //右侧文字按钮
    private void addRightTextBtn() {
        btnRight = new TextView(context);
        btnRight.setText(rightBtnText);
        btnRight.setTextColor(rightBtnTextColor);
        btnRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightBtnTextSize);
        btnRight.setPadding(ZQTools.dp2px(context, 14), 0, ZQTools.dp2px(context, 14), 0);
        btnRight.setGravity(Gravity.CENTER_VERTICAL);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(btnRight, leftParams);

        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onRightTextBtnClickListener) {
                    onRightTextBtnClickListener.onClick(view);
                }
            }
        });
    }

    //右侧图片按钮
    private void addRightIbtn() {
        ibtnRight = new ImageButton(context);
        ibtnRight.setPadding(ZQTools.dp2px(context, 14), 0, ZQTools.dp2px(context, 14), 0);
        ibtnRight.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ibtnRight.setImageDrawable(rightBackIcon);
        ibtnRight.setBackground(null);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(ibtnRight, leftParams);

        ibtnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onIconRightBtnClickListener) {
                    onIconRightBtnClickListener.onClick(view);
                }
            }
        });
    }


    public void setOnTextBtnClickListener(OnTextBtnClickListener onTextBtnClickListener) {
        this.onTextBtnClickListener = onTextBtnClickListener;
    }

    public void setOnIconBackBtnClickListener(OnIconBackBtnClickListener onIconBackBtnClickListener) {
        this.onIconBackBtnClickListener = onIconBackBtnClickListener;
    }

    public void setOnRightTextBtnClickListener(OnRightTextBtnClickListener onRightTextBtnClickListener) {
        this.onRightTextBtnClickListener = onRightTextBtnClickListener;
    }

    public void setOnIconRightBtnClickListener(OnIconRightBtnClickListener onIconRightBtnClickListener) {
        this.onIconRightBtnClickListener = onIconRightBtnClickListener;
    }


    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public float getTitltTextSize() {
        return titltTextSize;
    }

    public void setTitltTextSize(float titltTextSize) {
        this.titltTextSize = titltTextSize;
    }

    public String getBackBtnText() {
        return backBtnText;
    }

    public void setBackBtnText(String backBtnText) {
        this.backBtnText = backBtnText;
    }

    public int getBackBtnTextColor() {
        return backBtnTextColor;
    }

    public void setBackBtnTextColor(int backBtnTextColor) {
        this.backBtnTextColor = backBtnTextColor;
    }

    public float getBackBtnTextSize() {
        return backBtnTextSize;
    }

    public void setBackBtnTextSize(float backBtnTextSize) {
        this.backBtnTextSize = backBtnTextSize;
    }

    public Drawable getIbtnBackIcon() {
        return ibtnBackIcon;
    }

    public void setIbtnBackIcon(Drawable ibtnBackIcon) {
        this.ibtnBackIcon = ibtnBackIcon;
    }

    public String getRightBtnText() {
        return rightBtnText;
    }

    public void setRightBtnText(String rightBtnText) {
        this.rightBtnText = rightBtnText;
    }

    public int getRightBtnTextColor() {
        return rightBtnTextColor;
    }

    public void setRightBtnTextColor(int rightBtnTextColor) {
        this.rightBtnTextColor = rightBtnTextColor;
    }

    public float getRightBtnTextSize() {
        return rightBtnTextSize;
    }

    public void setRightBtnTextSize(float rightBtnTextSize) {
        this.rightBtnTextSize = rightBtnTextSize;
    }

    public Drawable getRightBackIcon() {
        return rightBackIcon;
    }

    public void setRightBackIcon(Drawable rightBackIcon) {
        this.rightBackIcon = rightBackIcon;
    }
}
