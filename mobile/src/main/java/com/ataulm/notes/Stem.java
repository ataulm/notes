package com.ataulm.notes;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Stem {

    static Stem create(Position start, Position end) {
        return new AutoValue_Stem(start, end);
    }

    abstract Position start();

    abstract Position end();

}
