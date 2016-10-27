package com.ataulm.notes;

public class Fixture {

    public static Measure createMeasure() {
        Bucket f = new Bucket(new Note(new Pitch(Step.F, Octave.ONE_LINE), Beat.CROCHET));
        Bucket a = new Bucket(new Note(new Pitch(Step.A, Octave.ONE_LINE), Beat.CROCHET));
        Bucket c = new Bucket(new Note(new Pitch(Step.C, Octave.TWO_LINE), Beat.CROCHET));
        Bucket e = new Bucket(new Note(new Pitch(Step.E, Octave.TWO_LINE), Beat.CROCHET));
        return new Measure(f, a, c, e);
    }

}
