package com.sudasell.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextSemiBold extends EditText {

    public EditTextSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextSemiBold(Context context) {
        super(context);
        init();
    }

    public void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ProximaNova-Semibold.otf");
        setTypeface(tf);
        // setTextColor(Color.WHITE); {
    }
}
