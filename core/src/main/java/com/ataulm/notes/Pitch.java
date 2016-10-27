package com.ataulm.notes;

class Pitch {

    private final Step step;
    private final Octave octave;
    private final Accidental accidental;

    Pitch(Step step, Octave octave) {
        this(step, octave, Accidental.NONE);
    }

    Pitch(Step step, Octave octave, Accidental accidental) {
        this.step = step;
        this.octave = octave;
        this.accidental = accidental;
    }

}
