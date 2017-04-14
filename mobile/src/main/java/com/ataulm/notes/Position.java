package com.ataulm.notes;

import android.support.annotation.Px;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Position {

    static Position create(@Px int x, @Px int y) {
        return new AutoValue_Position(x, y);
    }

    abstract int x();

    abstract int y();

}
