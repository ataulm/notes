package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class DebugAligningViewGroup extends FrameLayout {

    private final Paint paint;

    public DebugAligningViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = 0;
        for (int i = 0; i < getChildCount(); i++) {
            NoteWidget child = (NoteWidget) getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            width += childWidth;
        }

        int halfHeight = (int) (getHeight() * 0.5 + 0.5f);
        int childLeft = (int) ((getMeasuredWidth() - width) * 0.5);
        for (int i = 0; i < getChildCount(); i++) {
            NoteWidget child = (NoteWidget) getChildAt(i);
            int childRight = childLeft + child.getMeasuredWidth();
            int childTop = (int) ((halfHeight - child.noteCenterY()) + 0.5f);
            int childBottom = childTop + child.getMeasuredHeight();
            child.layout(childLeft, childTop, childRight, childBottom);
            childLeft = childRight;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float y = (float) (getHeight() * 0.5);
        canvas.drawLine(0, y, getWidth(), y, paint);
    }

}
