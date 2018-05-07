package com.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomLightTextView extends AppCompatTextView {
    public CustomLightTextView(Context context) {
        super(context);
        init();
    }

    public CustomLightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

/*
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomLightTextview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }*/

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), TypeFace.LightTF);
        setTypeface(tf);
        setLineSpacing(0.0f, 1.1f);
    }
}
