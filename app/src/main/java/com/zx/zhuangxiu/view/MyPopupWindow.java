package com.zx.zhuangxiu.view;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by 99477 on 2018/1/3.
 */

public class MyPopupWindow extends PopupWindow {


    public MyPopupWindow(View contentView) {
        super(contentView);
    }

    public MyPopupWindow(int width, int height) {
        super(width, height);
    }

    public MyPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public MyPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchorView, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] a = new int[2];
            anchorView.getLocationInWindow(a);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, xoff, a[1] + anchorView.getHeight() + yoff);
        } else {
            super.showAsDropDown(anchorView, xoff, yoff);
        }
    }

    @Override
    public void showAsDropDown(View anchorView) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] a = new int[2];
            anchorView.getLocationInWindow(a);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, a[1] + anchorView.getHeight() + 0);
        } else {
            super.showAsDropDown(anchorView);
        }
    }
}
