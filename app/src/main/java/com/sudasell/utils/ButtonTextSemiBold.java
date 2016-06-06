package com.sudasell.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


public class ButtonTextSemiBold extends Button {
    public ButtonTextSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ButtonTextSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonTextSemiBold(Context context) {
        super(context);
        init();
    }

    public void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProximaNova-Semibold.otf");
        setTypeface(tf);
        //setLineSpacing(10f, 1f);
    }
}
