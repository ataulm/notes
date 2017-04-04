package com.ataulm.notes.legacy;

import android.graphics.drawable.Drawable;

class OffsetDrawable {
    public final Drawable drawable;
    public final int canvasDx;
    public final int canvasDy;

    OffsetDrawable(Drawable drawable, int canvasDx, int canvasDy) {
        this.drawable = drawable;
        this.canvasDx = canvasDx;
        this.canvasDy = canvasDy;
    }

    @Override
    public String toString() {
        return "drawable(" + super.toString() + "): dx(" + canvasDx + "), dy(" + canvasDy + ")";
    }
}
