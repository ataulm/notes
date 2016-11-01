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

    public Step step() {
        return step;
    }

    public Octave octave() {
        return octave;
    }

    public Accidental accidental() {
        return accidental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pitch pitch = (Pitch) o;

        if (step != pitch.step) {
            return false;
        }
        if (octave != pitch.octave) {
            return false;
        }
        return accidental == pitch.accidental;

    }

    @Override
    public int hashCode() {
        int result = step.hashCode();
        result = 31 * result + octave.hashCode();
        result = 31 * result + accidental.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return step()
                + accidentalToString()
                + octave().scientificNotation();
    }

    private String accidentalToString() {
        return accidental().isPresent() ? (accidental.isSharp() ? "#" : "b") : "";
    }
}
