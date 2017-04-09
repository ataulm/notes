package com.ataulm.notes;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class ConcurrentNotesWidget extends View {

    private final ConcurrentNotesWidgetSizeCalculator calculator;

    private Size size;

    public ConcurrentNotesWidget(Context context) {
        super(context);
        MusicalSymbolSizes musicSymbolSizes = new MusicalSymbolSizes(
                Size.create(10, 10),
                Size.create(10, 10),
                Size.create(10, 10),
                Size.create(10, 10)
        );
        calculator = new ConcurrentNotesWidgetSizeCalculator(musicSymbolSizes, new PositionsApartFromMiddleCCalculator());
        setBackgroundColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(size.width(), size.height());
    }

    void bind(Key key, ConcurrentNotes notes) {
        size = calculator.size(key, notes);
        requestLayout();
    }

}
