package com.smile.titlebar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Smile on 2017/9/30.
 */

public class ZQTitleView extends RelativeLayout {

    private Context context;

    private TextView tvTitle;

    private String titleText = "标题";
    private int titleTextColor = Color.parseColor("#f0ff0f");
    private int titltTextSize = 16;

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

        adaTitleTextView();
    }

    private void adaTitleTextView() {
        tvTitle = new TextView(context);
        tvTitle.setText(titleText);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titltTextSize);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tvTitle, leftParams);
    }
}
