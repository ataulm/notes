package com.ataulm.notes;

import java.util.Arrays;
import java.util.List;

public class Fixture {

    public static List<OldNote> createNotes() {
        return Arrays.asList(
                new OldNote(new Pitch(Step.E, Octave.ONE_LINE), Staff.TREBLE),
                new OldNote(new Pitch(Step.G, Octave.ONE_LINE), Staff.TREBLE),
                new OldNote(new Pitch(Step.B, Octave.ONE_LINE), Staff.TREBLE),
                new OldNote(new Pitch(Step.D, Octave.TWO_LINE), Staff.TREBLE)
        );
    }

}
