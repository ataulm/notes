package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class ConcurrentNotesWidget extends View {

    private final PositionsApartFromMiddleCCalculator positionCalculator;
    private final MusicalSymbolSizes symbolSizes;
    private final Drawable noteDrawable;
    private ConcurrentNotesWidgetCalculator.Output output;

    public ConcurrentNotesWidget(Context context) {
        super(context);

        positionCalculator = new PositionsApartFromMiddleCCalculator();
        symbolSizes = MusicalSymbolSizes.create();
        noteDrawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        noteDrawable.setBounds(0, 0, symbolSizes.note.width(), symbolSizes.note.height());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(output.widgetSize().width(), output.widgetSize().height());
    }

    void bind(Key key, ConcurrentNotes notes) {
        this.notes = notes;


        ConcurrentNotesWidgetCalculator calculator = new ConcurrentNotesWidgetCalculator(symbolSizes, positionCalculator);
        output = calculator.size(key, notes);
        requestLayout();
    }

    ConcurrentNotes notes;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        noteDrawable.draw(canvas);
    }

    @Px
    public int topToMiddleC() {
        return output.widgetTopToMiddleC();
    }

}
