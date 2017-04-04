package com.ataulm.notes.legacy;

enum Step {

    C(0), D(1), E(2), F(3), G(4), A(5), B(6);

    private final int indexInOctave;

    Step(int indexInOctave) {
        this.indexInOctave = indexInOctave;
    }

    public int indexInOctave() {
        return indexInOctave;
    }

}
