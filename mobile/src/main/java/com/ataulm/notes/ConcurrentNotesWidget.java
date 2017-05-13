package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.List;

public class ConcurrentNotesWidget extends View {

    private final Drawable noteDrawable;
    private final Drawable sharpDrawable;
    private final Drawable naturalDrawable;
    private final ConcurrentNotesPositionCalculator positionCalculator;
    private final ConcurrentNotesSizer notesSizer;
    private final MusicalSymbolSizes symbolSizes;

    private List<PositionedMark> positionedMarks;
    private int topToMiddleCee;

    public ConcurrentNotesWidget(Context context) {
        super(context);

        MiddleCeeOffsetCalculator offsetCalculator = new MiddleCeeOffsetCalculator();
        symbolSizes = MusicalSymbolSizes.create(getResources());

        positionCalculator = new ConcurrentNotesPositionCalculator(symbolSizes, offsetCalculator);
        notesSizer = new ConcurrentNotesSizer(symbolSizes);

        noteDrawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        noteDrawable.setBounds(0, 0, symbolSizes.note.width(), symbolSizes.note.height());

        sharpDrawable = ContextCompat.getDrawable(context, R.drawable.vec_sharp);
        sharpDrawable.setBounds(0, 0, symbolSizes.sharp.width(), symbolSizes.sharp.height());

        naturalDrawable = ContextCompat.getDrawable(context, R.drawable.vec_natural);
        naturalDrawable.setBounds(0, 0, symbolSizes.natural.width(), symbolSizes.natural.height());
    }

    void bind(Key key, ConcurrentNotes notes) {
        positionedMarks = positionCalculator.calculatePositions(key, notes);
        topToMiddleCee = positionCalculator.topToMiddleCee(key, notes);
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (positionedMarks == null) {
            throw new IllegalStateException("should bind notes before adding this view");
        }
        Size size = notesSizer.size(positionedMarks);
        setMeasuredDimension(size.width(), size.height());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (PositionedMark positionedMark : positionedMarks) {
            Mark mark = positionedMark.mark();
            Drawable drawable = getDrawableFor(mark);
            int count = canvas.save();
            canvas.translate(positionedMark.center().x() - halfWidth(mark), positionedMark.center().y() - halfHeight(mark));
            drawable.draw(canvas);
            canvas.restoreToCount(count);
        }
    }

    private int halfWidth(Mark mark) {
        switch (mark) {
            case NATURAL:
                return (int) (symbolSizes.natural.width() * 0.5);
            case SHARP:
                return (int) (symbolSizes.sharp.width() * 0.5);
            case NOTE:
                return (int) (symbolSizes.note.width() * 0.5);
            default:
                throw new IllegalArgumentException("unhandled mark: " + mark);
        }
    }

    private int halfHeight(Mark mark) {
        switch (mark) {
            case NATURAL:
                return (int) (symbolSizes.natural.height() * 0.5);
            case SHARP:
                return (int) (symbolSizes.sharp.height() * 0.5);
            case NOTE:
                return (int) (symbolSizes.note.height() * 0.5);
            default:
                throw new IllegalArgumentException("unhandled mark: " + mark);
        }
    }

    private Drawable getDrawableFor(Mark mark) {
        switch (mark) {
            case NATURAL:
                return naturalDrawable;
            case SHARP:
                return sharpDrawable;
            case NOTE:
                return noteDrawable;
            default:
                throw new IllegalArgumentException("unhandled mark: " + mark);
        }
    }

    @Px
    public int topToMiddleCee() {
        return topToMiddleCee;
    }

}
