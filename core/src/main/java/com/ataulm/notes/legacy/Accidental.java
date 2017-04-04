package com.ataulm.notes.legacy;

enum Accidental {

    SHARP,
    FLAT,
    NONE;

    public boolean isPresent() {
        return this != NONE;
    }

    public boolean isSharp() {
        return this == SHARP;
    }

}
