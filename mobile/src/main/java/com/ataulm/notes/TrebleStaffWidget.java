package com.ataulm.notes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Supports displaying notes E3 to F6 (12 * note.height. E3==space, F6==space)
 */
public class TrebleStaffWidget extends FrameLayout {

    private static final int WINDOW_SIZE = 4;

    private final Paint paint;
    private final Paint red;
    private final Drawable trebleClefDrawable;
    private final TrebleStaffSizer trebleStaffSizer;
    private final MusicalSymbolSizes symbolSizes;

    private Key key = Key.C_MAJ;

    public TrebleStaffWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        trebleStaffSizer = new TrebleStaffSizer(MusicalSymbolSizes.create(context.getResources()));
        trebleClefDrawable = ContextCompat.getDrawable(context, R.drawable.vec_treble_clef);
        Size trebleClefSize = trebleStaffSizer.clefSize();
        trebleClefDrawable.setBounds(0, 0, trebleClefSize.width(), trebleClefSize.height());

        paint = new Paint();
        paint.setColor(Color.BLACK);

        red = new Paint();
        red.setColor(Color.RED);
        symbolSizes = MusicalSymbolSizes.create(context.getResources());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int exactHeightMeasureSpec = MeasureSpec.makeMeasureSpec(trebleStaffSizer.height(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, exactHeightMeasureSpec);
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
        int middleCY = trebleStaffSizer.middleCY();

        for (int i = 0; i < getChildCount(); i++) {
            ConcurrentNotesWidget child = (ConcurrentNotesWidget) getChildAt(i);
            int childLeft = 60 + i * 75;
            int childTop = middleCY + child.topToMiddleCee();

            child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.save();
        Position clefPosition = trebleStaffSizer.clefPosition();
        canvas.translate(clefPosition.x(), clefPosition.y());
        trebleClefDrawable.draw(canvas);
        canvas.restoreToCount(saveCount);
        drawStaff(canvas);

        int trebleStaffTop = trebleStaffSizer.topLine();
        int trebleStaffBottom = trebleStaffSizer.bottomLine();
        for (int i = 0; i < getChildCount(); i++) {
            View notesWidget = getChildAt(i);
            drawLedgerLinesAboveStaff(canvas, notesWidget.getLeft(), notesWidget.getTop(), notesWidget.getRight(), trebleStaffTop);
            drawLedgerLinesBeneathStaff(canvas, notesWidget.getLeft(), trebleStaffBottom, notesWidget.getRight(), notesWidget.getBottom());
        }
    }

    private void drawLedgerLinesAboveStaff(Canvas canvas, int left, int childTop, int right, int staffTopLine) {
        if (staffTopLine < childTop) {
            return;
        }
        float spacing =  symbolSizes.note.height();
        float currentY = staffTopLine;
        while (currentY > childTop) {
            currentY -= spacing;
            canvas.drawLine(left, currentY, right, currentY, paint);
        }
    }

    private void drawLedgerLinesBeneathStaff(Canvas canvas, int left, int staffBottomLine, int right, int childBottom) {
        if (childBottom < staffBottomLine) {
            return;
        }
        float spacing =  symbolSizes.note.height();
        float currentY = staffBottomLine;
        while (currentY < childBottom) {
            currentY += spacing;
            canvas.drawLine(left, currentY, right, currentY, paint);
        }
    }

    private void drawStaff(Canvas canvas) {
        int viewWidth = getWidth();

        int noteHeight = symbolSizes.note.height();
        int lineStartX = noteHeight;
        int lineEndX = viewWidth - lineStartX;

        for (int lineY : trebleStaffSizer.linesY()) {
            canvas.drawLine(lineStartX, lineY, lineEndX, lineY, paint);
        }

        canvas.drawLine(lineStartX, 5 * noteHeight, lineStartX, 9 * noteHeight, paint);
        canvas.drawLine(lineEndX, 5 * noteHeight, lineEndX, 9 * noteHeight, paint);
    }

}
