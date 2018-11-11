package com.zx.zhuangxiu.view;

/**
 * Created by Administrator on 2017/12/22 0022.
 * 自定义滚动通知
 */
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.myinterface.MarqueeTextViewClickListener;

public class MarqueeTextView extends LinearLayout {
    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
    private String[] textArrays;
    private MarqueeTextViewClickListener marqueeTextViewClickListener;
    public MarqueeTextView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }
    public void setTextArraysAndClickListener(String[] textArrays, MarqueeTextViewClickListener marqueeTextViewClickListener) {
        this.textArrays = textArrays;
        this.marqueeTextViewClickListener = marqueeTextViewClickListener;
        initMarqueeTextView(textArrays, marqueeTextViewClickListener);
    }

    public void initBasicView() {
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.marquee_textview_layout, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);
        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        viewFlipper.startFlipping();

    }
    public void initMarqueeTextView(String[] textArrays, MarqueeTextViewClickListener marqueeTextViewClickListener) {
        if (textArrays.length == 0) {
            return;
        }

        int i = 0;
        viewFlipper.removeAllViews();
        while (i < textArrays.length) {
            TextView textView = new TextView(mContext);
            textView.setText(textArrays[i]);
            textView.setTextSize(16);
            textView.setGravity(Gravity.CENTER_VERTICAL);//设置文字居中
            textView.setTextColor(this.getResources().getColor(R.color.black));//设置通知栏字体颜色
            textView.setOnClickListener(marqueeTextViewClickListener);
            LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewFlipper.addView(textView, lp);
            i++;
        }
    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }
}
