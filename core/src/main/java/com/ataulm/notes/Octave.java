package com.ataulm.notes;

enum Octave {

    CONTRA(1),
    GREAT(2),
    SMALL(3),
    ONE_LINE(4),
    TWO_LINE(5),
    THREE_LINE(6),
    FOUR_LINE(7),
    FIVE_LINE(8);

    private final int scientificNotation;

    Octave(int scientificNotation) {
        this.scientificNotation = scientificNotation;
    }

    public int scientificNotation() {
        return scientificNotation;
    }

}
