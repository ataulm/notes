package com.ataulm.notes;

import android.support.annotation.Px;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class PositionedMark {

    static PositionedMark create(Mark mark, @Px int x, @Px int y) {
        return new AutoValue_PositionedMark(mark, Position.create(x, y));
    }

    abstract Mark mark();

    abstract Position center();

    PositionedMark x(@Px int x) {
        return create(mark(), x, center().y());
    }

    PositionedMark y(@Px int y) {
        return create(mark(), center().x(), y);
    }

}
