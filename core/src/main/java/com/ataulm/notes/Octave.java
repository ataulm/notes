package com.ataulm.notes;

class Octave {

    private final int scientificValue;

    private Octave(int scientificValue) {
        this.scientificValue = scientificValue;
    }

    static Octave create(int scientificValue) {
        if (scientificValue < -1 || scientificValue > 9) {
            throw new IllegalArgumentException("Invalid octave. Scientific notation for octaves range from -1 to 9, inclusive.");
        }
        return new Octave(scientificValue);
    }

    public int scientificValue() {
        return scientificValue;
    }

}
