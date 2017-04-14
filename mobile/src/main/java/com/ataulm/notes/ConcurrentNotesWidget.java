package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Px;
import android.view.View;

public class ConcurrentNotesWidget extends View {

    private final PositionsApartFromMiddleCCalculator positionCalculator;
    private final MusicalSymbolSizes symbolSizes;
    private ConcurrentNotesWidgetCalculator.Output output;

    public ConcurrentNotesWidget(Context context) {
        super(context);
        setBackgroundColor(Color.BLACK);

        positionCalculator = new PositionsApartFromMiddleCCalculator();
        symbolSizes = MusicalSymbolSizes.create();
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
        // TODO: draw
    }

    @Px
    public int topToMiddleC() {
        return output.widgetTopToMiddleC();
    }

}
