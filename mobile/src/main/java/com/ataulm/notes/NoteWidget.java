package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Each noteWidget will display a single note and its accidental if available.
 * <p>
 * We'll ignore the fact that accidentals can overlap if multiple notes are play concurrently;
 * we will assume that such concurrent notes will not occur in this app.
 * <p>
 * We could have multiple NoteWidget subclasses:
 * <p>
 * - SimpleNoteWidget
 * - SharpNoteWidget
 * - FlatNoteWidget
 * - NaturalNoteWidget
 * <p>
 * There are two important things to consider:
 * <p>
 * - the width and height should be respectful of the entire bounds of this View's drawables
 * - the "note center" should be exposed by some method, which will differ in each of the
 * implementations and will be given as x,y values relative to the top left of this View
 */
public abstract class NoteWidget extends View {

    protected final MusicalSymbolSizes sizes;

    public static NoteWidget simple(Context context) {
        return new Simple(context);
    }

    public static NoteWidget sharp(Context context) {
        return new Sharp(context);
    }

    public static NoteWidget flat(Context context) {
        return new Flat(context);
    }

    public static NoteWidget natural(Context context) {
        return new Natural(context);
    }

    protected NoteWidget(Context context) {
        super(context);
        sizes = MusicalSymbolSizes.create(context.getResources());
    }

}

class Simple extends NoteWidget {

    private final Drawable note;

    Simple(Context context) {
        super(context);
        this.note = createNoteDrawable(context);
    }

    private Drawable createNoteDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        drawable.setBounds(0, 0, sizes.note.width(), sizes.note.height());
        return drawable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(sizes.note.width(), sizes.note.height());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        note.draw(canvas);
    }

}

class Sharp extends NoteWidget {

    private final Drawable sharp;
    private final Drawable note;

    Sharp(Context context) {
        super(context);
        this.sharp = createSharpDrawable(context);
        this.note = createNoteDrawable(context);
    }

    private Drawable createSharpDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_sharp);
        drawable.setBounds(0, 0, sizes.sharp.width(), sizes.sharp.height());
        return drawable;
    }

    private Drawable createNoteDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        drawable.setBounds(0, 0, sizes.note.width(), sizes.note.height());
        return drawable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = sizes.sharp.width() + sizes.note.width();
        int height = Math.max(sizes.sharp.height(), sizes.note.height());
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sharp.draw(canvas);
        int count = canvas.save();
        canvas.translate(sizes.sharp.width(), (float) ((getHeight() - sizes.note.height()) * 0.5));
        note.draw(canvas);
        canvas.restoreToCount(count);
    }

}

class Flat extends NoteWidget {

    private final Drawable flat;
    private final Drawable note;

    Flat(Context context) {
        super(context);
        this.flat = createFlatDrawable(context);
        this.note = createNoteDrawable(context);
    }

    private Drawable createFlatDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_flat);
        drawable.setBounds(0, 0, sizes.flat.width(), sizes.flat.height());
        return drawable;
    }

    private Drawable createNoteDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        drawable.setBounds(0, 0, sizes.note.width(), sizes.note.height());
        return drawable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = sizes.flat.width() + sizes.note.width();
        int height = Math.max(sizes.flat.height(), sizes.note.height());
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        flat.draw(canvas);
        int count = canvas.save();
        canvas.translate(sizes.flat.width(), (float) (getHeight() - sizes.note.height() - (sizes.note.height() * 0.15)));
        note.draw(canvas);
        canvas.restoreToCount(count);
    }

}

class Natural extends NoteWidget {

    private final Drawable natural;
    private final Drawable note;

    Natural(Context context) {
        super(context);
        this.natural = createNaturalDrawable(context);
        this.note = createNoteDrawable(context);
    }

    private Drawable createNaturalDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_natural);
        drawable.setBounds(0, 0, sizes.natural.width(), sizes.natural.height());
        return drawable;
    }

    private Drawable createNoteDrawable(Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.vec_whole_note);
        drawable.setBounds(0, 0, sizes.note.width(), sizes.note.height());
        return drawable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = sizes.natural.width() + sizes.note.width();
        int height = Math.max(sizes.natural.height(), sizes.note.height());
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        natural.draw(canvas);
        int count = canvas.save();
        canvas.translate(sizes.natural.width(), (float) ((getHeight() - sizes.note.height()) * 0.5));
        note.draw(canvas);
        canvas.restoreToCount(count);
    }

}
