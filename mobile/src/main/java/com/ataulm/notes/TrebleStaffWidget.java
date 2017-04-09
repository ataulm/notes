package com.ataulm.notes;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class TrebleStaffWidget extends ViewGroup {

    private Key key = Key.C_MAJ;

    public TrebleStaffWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void setKey(Key key) {
        this.key = key;
    }

    void bind(Sequence sequence) {
        removeAllViews();

        for (ConcurrentNotes notes : sequence.notes()) {
            ConcurrentNotesWidget concurrentNotesWidget = new ConcurrentNotesWidget(getContext());
            concurrentNotesWidget.bind(key, notes);
            addView(concurrentNotesWidget);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(0);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(0);
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

}
