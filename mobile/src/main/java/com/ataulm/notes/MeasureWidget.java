package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MeasureWidget extends FrameLayout {

    private List<NoteDrawable> drawables;

    public MeasureWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundResource(R.drawable.trebelclef);
        Measure measure = Fixture.createMeasure();
        update(measure);
    }

    public void update(Measure measure) {
        // http://www.musicreadingsavant.com/the-stem-rule-how-to-know-what-direction-the-stems-should-go/
        // Just remember that notes on the third line and above have stems down on the left and anything below that is up on the right.

        drawables = new ArrayList<>(measure.notes());

        for (int i = 0; i < measure.size(); i++) {
            Bucket notes = measure.get(i);
            for (Note note : notes) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.crochet_down);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

                NoteDrawable noteDrawable = new NoteDrawable(drawable, i * drawable.getIntrinsicWidth(), 0);
                drawables.add(noteDrawable);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (NoteDrawable drawable : drawables) {
            int save = canvas.save();
            canvas.translate(drawable.canvasDx, drawable.canvasDy);
            drawable.note.draw(canvas);
            canvas.restoreToCount(save);
            Log.i("!!!", drawable.toString());
        }
    }

    private static class NoteDrawable {
        public final Drawable note;
        public final int canvasDx;
        public final int canvasDy;

        private NoteDrawable(Drawable note, int canvasDx, int canvasDy) {
            this.note = note;
            this.canvasDx = canvasDx;
            this.canvasDy = canvasDy;
        }

        @Override
        public String toString() {
            return "note("+ super.toString() + "): dx(" + canvasDx + "), dy(" + canvasDy + ")";
        }
    }

}
