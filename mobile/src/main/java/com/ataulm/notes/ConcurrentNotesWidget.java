package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class ConcurrentNotesWidget extends View {

    private final MiddleCeeOffsetCalculator positionCalculator;
    private final MusicalSymbolSizes symbolSizes;
    private final Drawable noteDrawable;
    private ConcurrentNotesWidgetHelper.Output output;

    public ConcurrentNotesWidget(Context context) {
        super(context);

        setBackgroundColor(Color.YELLOW);
        positionCalculator = new MiddleCeeOffsetCalculator();
        symbolSizes = MusicalSymbolSizes.create(getResources());
        noteDrawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        noteDrawable.setBounds(0, 0, symbolSizes.note.width(), symbolSizes.note.height());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(output.widgetSize().width(), output.widgetSize().height());
    }

    void bind(Key key, ConcurrentNotes notes) {
        ConcurrentNotesWidgetHelper calculator = new ConcurrentNotesWidgetHelper(symbolSizes, positionCalculator);
        output = calculator.size(key, notes);
        requestLayout();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Position position : output.notes()) {
            int count = canvas.save();
            canvas.translate(position.x(), position.y());
            noteDrawable.draw(canvas);
            canvas.restoreToCount(count);
        }
    }

    @Px
    public int topToMiddleC() {
        return output.widgetTopToMiddleC();
    }

}
