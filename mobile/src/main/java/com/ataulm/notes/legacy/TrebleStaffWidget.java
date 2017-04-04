package com.ataulm.notes.legacy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

class TrebleStaffWidget extends FrameLayout implements NotesView {

    private final Drawable noteDrawable;
    private final int notesInitialOffsetLeftForClefMarkPx;
    private final int notesOffsetLeftPx;
    private final int notesInitialOffsetTopToMiddleC;
    private final TrebleStaffPitchOffsetFromMiddleCCalculator pitchOffsetCalculator;

    private List<OffsetDrawable> drawables;

    public TrebleStaffWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        noteDrawable = ContextCompat.getDrawable(getContext(), R.drawable.circle);
        int noteWidthPx = noteDrawable.getIntrinsicWidth();
        int noteHeightPx = noteDrawable.getIntrinsicHeight();
        noteDrawable.setBounds(0, 0, noteWidthPx, noteHeightPx);

        Resources res = context.getResources();
        notesInitialOffsetLeftForClefMarkPx = res.getDimensionPixelSize(R.dimen.notes_initial_offset_left_for_clef_mark);
        int spaceBetweenNotesPx = res.getDimensionPixelSize(R.dimen.space_between_notes);
        notesOffsetLeftPx = noteWidthPx + spaceBetweenNotesPx;

        int stepOffset = res.getDimensionPixelSize(R.dimen.step_offset);
        notesInitialOffsetTopToMiddleC = res.getDimensionPixelSize(R.dimen.notes_initial_offset_top_to_middle_c);
        pitchOffsetCalculator = new TrebleStaffPitchOffsetFromMiddleCCalculator(stepOffset);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundResource(R.drawable.trebelclef);
    }

    @Override
    public void update(List<NoteViewModel> viewModels) {
        assertAllTrebleNotes(viewModels);

        drawables = new ArrayList<>(viewModels.size());
        for (int i = 0; i < viewModels.size(); i++) {
            NoteViewModel viewModel = viewModels.get(i);

            int canvasDx = notesInitialOffsetLeftForClefMarkPx + i * notesOffsetLeftPx;
            int canvasDy = notesInitialOffsetTopToMiddleC + pitchOffsetCalculator.canvasOffsetY(viewModel.note().pitch());
            OffsetDrawable offsetDrawable = new OffsetDrawable(noteDrawable, canvasDx, canvasDy);
            drawables.add(offsetDrawable);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawables == null) {
            return;
        }
        for (OffsetDrawable drawable : drawables) {
            int save = canvas.save();
            canvas.translate(drawable.canvasDx, drawable.canvasDy);
            drawable.drawable.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    private static void assertAllTrebleNotes(List<NoteViewModel> noteViewModels) {
        for (NoteViewModel noteViewModel : noteViewModels) {
            if (noteViewModel.note().staff() != Staff.TREBLE) {
                throw new IllegalStateException("TrebleStaffWidget can only support notes written for treble staff.");
            }
        }
    }

}
