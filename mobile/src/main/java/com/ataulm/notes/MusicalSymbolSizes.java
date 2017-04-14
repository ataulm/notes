package com.ataulm.notes;

class MusicalSymbolSizes {

    final Size note;
    final Size sharp;
    final Size flat;
    final Size natural;

    static MusicalSymbolSizes create() {
        // these should be fixed based on the symbol sizes which are known at compile time
        // users should not create with whatever parameters they choose - this should be fixed, systemwide
        return new MusicalSymbolSizes(
                Size.create(16, 10),
                Size.create(10, 10),
                Size.create(10, 10),
                Size.create(10, 10)
        );
    }

    MusicalSymbolSizes(Size note, Size sharp, Size flat, Size natural) {
        this.note = note;
        this.sharp = sharp;
        this.flat = flat;
        this.natural = natural;
    }

}
