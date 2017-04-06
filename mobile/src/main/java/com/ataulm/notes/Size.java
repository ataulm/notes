package com.ataulm.notes;

import android.support.annotation.Px;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Size {

    public static Size create(@Px int width, @Px int height) {
        return new AutoValue_Size(width, height);
    }

    abstract int width();

    abstract int height();

}
