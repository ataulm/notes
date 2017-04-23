package com.ataulm.notes;

import android.content.res.Resources;

class MusicalSymbolSizes {

    final Size note;
    final Size sharp;
    final Size flat;
    final Size natural;

    static MusicalSymbolSizes create(Resources resources) {
        return new MusicalSymbolSizes(
                Size.create(resources.getDimensionPixelSize(R.dimen.note_width), resources.getDimensionPixelSize(R.dimen.note_height)),
                Size.create(resources.getDimensionPixelSize(R.dimen.sharp_width), resources.getDimensionPixelSize(R.dimen.sharp_height)),
                Size.create(resources.getDimensionPixelSize(R.dimen.flat_width), resources.getDimensionPixelSize(R.dimen.flat_height)),
                Size.create(resources.getDimensionPixelSize(R.dimen.natural_width), resources.getDimensionPixelSize(R.dimen.natural_height))
        );
    }

    MusicalSymbolSizes(Size note, Size sharp, Size flat, Size natural) {
        this.note = note;
        this.sharp = sharp;
        this.flat = flat;
        this.natural = natural;
    }

}
