package com.ataulm.notes;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class NoteMark {

    static NoteMark create(Note note, Mark mark) {
        return new AutoValue_NoteMark(note, mark);
    }

    abstract Note note();

    abstract Mark mark();

}
