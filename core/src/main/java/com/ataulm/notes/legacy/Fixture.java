package com.ataulm.notes.legacy;

import java.util.Arrays;
import java.util.List;

public class Fixture {

    public static List<Note> createNotes() {
        return Arrays.asList(
                new Note(new Pitch(Step.E, Octave.ONE_LINE), Staff.TREBLE),
                new Note(new Pitch(Step.G, Octave.ONE_LINE), Staff.TREBLE),
                new Note(new Pitch(Step.B, Octave.ONE_LINE), Staff.TREBLE),
                new Note(new Pitch(Step.D, Octave.TWO_LINE), Staff.TREBLE)
        );
    }

}
