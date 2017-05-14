package com.ataulm.notes;

import com.ataulm.Optional;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
abstract class ConcurrentNotesMarks {

    static ConcurrentNotesMarks create(List<PositionedMark> marks, Optional<Stem> stem) {
        return new AutoValue_ConcurrentNotesMarks(marks, stem);
    }

    abstract List<PositionedMark> marks();

    abstract Optional<Stem> stem();

}
