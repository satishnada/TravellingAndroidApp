package com.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomBoldTextView extends AppCompatTextView {
    public CustomBoldTextView(Context context) {
        super(context);
        init();
    }

    public CustomBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
/*
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomBoldTextview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }*/

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), TypeFace.BoldTF);
        setTypeface(tf);
        //setLineSpacing(0.0f, 1.1f);

    }

}
