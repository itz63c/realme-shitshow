package com.coloros.deeptesting.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    /* renamed from: a */
    private Context f321a;

    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f321a = context;
    }

    public MyScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f321a = context;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        try {
            Display defaultDisplay = ((Activity) this.f321a).getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            i2 = View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels / 2, Integer.MIN_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(i, i2);
    }
}
