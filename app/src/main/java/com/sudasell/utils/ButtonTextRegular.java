package com.sudasell.utils;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonTextRegular extends Button {
    public ButtonTextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ButtonTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonTextRegular(Context context) {
        super(context);
        init();
    }

    public void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProximaNova-Regular.otf");
        setTypeface(tf);
        //setLineSpacing(10f, 1f);
    }
}
