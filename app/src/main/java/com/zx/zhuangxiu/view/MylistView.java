package com.zx.zhuangxiu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class MylistView extends ListView {
    public MylistView(Context context) {
        super(context);
    }

    public MylistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MylistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
