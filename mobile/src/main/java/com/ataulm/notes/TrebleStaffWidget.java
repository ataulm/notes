package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Supports displaying notes E3 to F6 (12 * note.height. E3==space, F6==space)
 */
public class TrebleStaffWidget extends FrameLayout {

    private Key key = Key.C_MAJ;

    private Drawable trebleClefDrawable;
    private static final int WINDOW_SIZE = 4;

    public TrebleStaffWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        trebleClefDrawable = ContextCompat.getDrawable(context, R.drawable.vec_treble_clef);
        trebleClefDrawable.setBounds(0, 0, 32, 90);
    }

    void setKey(Key key) {
        this.key = key;
    }

    void bind(Sequence sequence) {
        removeAllViews();

        List<ConcurrentNotes> window = buildCurrentWindowFrom(sequence);
        for (ConcurrentNotes notes : window) {
            ConcurrentNotesWidget concurrentNotesWidget = new ConcurrentNotesWidget(getContext());
            concurrentNotesWidget.bind(key, notes);
            addView(concurrentNotesWidget);
        }
    }

    private List<ConcurrentNotes> buildCurrentWindowFrom(Sequence sequence) {
        int length = sequence.length();
        if (length < WINDOW_SIZE) {
            return sequence.notes();
        }

        if (sequence.position() + WINDOW_SIZE - 1 <= sequence.length()) {
            return sequence.notes().subList(sequence.position(), sequence.position() + WINDOW_SIZE);
        } else {
            return sequence.notes().subList(length - WINDOW_SIZE, length);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childLeft = i * 20;
            child.layout(childLeft, 0, childLeft + child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        trebleClefDrawable.draw(canvas);
    }
}
