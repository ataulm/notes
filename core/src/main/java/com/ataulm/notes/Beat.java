package com.ataulm.notes;

enum Beat {

    SEMIQUAVER(0.25),
    QUAVER(0.5),
    CROCHET(1),
    MINIM(2),
    SEMIBREVE(4);

    private final double crochetValue;

    Beat(double crochetValue) {
        this.crochetValue = crochetValue;
    }

}
