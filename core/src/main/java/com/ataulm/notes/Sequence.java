package com.ataulm.notes;

import com.ataulm.Optional;
import com.google.auto.value.AutoValue;

import java.util.Arrays;
import java.util.List;

@AutoValue
public abstract class Sequence {

    public static Sequence create(ConcurrentNotes... notes) {
        List<ConcurrentNotes> list = Arrays.asList(notes);
        return new AutoValue_Sequence(list, 0, Optional.<ConcurrentNotes>absent());
    }

    public abstract List<ConcurrentNotes> notes();

    public abstract int position();

    public abstract Optional<ConcurrentNotes> incorrectNotes();

    public Sequence withPositionIncremented() {
        return new AutoValue_Sequence(notes(), position() + 1, Optional.<ConcurrentNotes>absent());
    }

    public Sequence withIncorrect(ConcurrentNotes notes) {
        return new AutoValue_Sequence(notes(), position(), Optional.of(notes));
    }

}
