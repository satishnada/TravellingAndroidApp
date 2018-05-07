package com.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class CustomLightEditText extends AppCompatEditText {
    public CustomLightEditText(Context context) {
        super(context);
        init();
    }

    public CustomLightEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLightEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), TypeFace.LightTF);
        setTypeface(tf);
        //setLineSpacing(0.0f, 1.0f);
    }
}
