package com.smile.titlebar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
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

    private String titleText = "标题";
    private int titleTextColor = Color.parseColor("#f0ff0f");
    private int titltTextSize = 16;

    private String backBtnText = "返回";
    private int backBtnTextColor = Color.parseColor("#fff000");
    private int backBtnTextSize = 14;

    private OnTextBtnClickListener onTextBtnClickListener = null;

    public ZQTitleView(Context context) {
        super(context);
        this.context = context;
        initViews();
    }

    public ZQTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initViews();
    }

    private void initViews() {

        addTitleTextView();
        addLeftTextBtn();
        addLeftIbtnBack();

    }

    //标题
    private void addTitleTextView() {
        tvTitle = new TextView(context);
        tvTitle.setText(titleText);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titltTextSize);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvTitle, leftParams);
    }

    //左侧文字返回按钮
    private void addLeftTextBtn() {
        btnBack = new TextView(context);
        btnBack.setText(backBtnText);
        btnBack.setTextColor(backBtnTextColor);
        btnBack.setTextSize(TypedValue.COMPLEX_UNIT_DIP, backBtnTextSize);
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
        ibtnBack.setBackground(null);
        ibtnBack.setImageResource(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
    }


    public void setOnTextBtnClickListener(OnTextBtnClickListener onTextBtnClickListener) {
        this.onTextBtnClickListener = onTextBtnClickListener;
    }
}
