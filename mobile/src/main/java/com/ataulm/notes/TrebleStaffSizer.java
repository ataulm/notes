package com.ataulm.notes;

import android.support.annotation.Px;

/*
0  ----------------------------

n  ----------------------------
                F6
2          -----C4-----

3n         -----C6-----

4n         ------------

5n ----------------------------

6n ----------------------------
                C5
7n ----------------------------

8n ----------------------------

9n ----------------------------

10n        -----C4-----

11n        ------------

12n        ------------
                E3
13n ---------------------------

14n ---------------------------
*/
class TrebleStaffSizer {

    private final MusicalSymbolSizes musicalSymbolSizes;

    TrebleStaffSizer(MusicalSymbolSizes musicalSymbolSizes) {
        this.musicalSymbolSizes = musicalSymbolSizes;
    }

    Size clefSize() {
        int noteHeight = musicalSymbolSizes.note.height();
        return Size.create(3 * noteHeight, 8 * noteHeight);
    }

    Position clefPosition() {
        int noteHeight = musicalSymbolSizes.note.height();
        return Position.create((int) (1.5 * noteHeight), 3 * noteHeight);
    }

    @Px
    int[] linesY() {
        int noteHeight = musicalSymbolSizes.note.height();
        return new int[]{
                5 * noteHeight,
                6 * noteHeight,
                7 * noteHeight,
                8 * noteHeight,
                9 * noteHeight
        };
    }

    int middleCY() {
        int noteHeight = musicalSymbolSizes.note.height();
        return 10 * noteHeight;
    }

    @Px
    int height() {
        return 14 * musicalSymbolSizes.note.height();
    }

    Position staffLineTop() {
        int noteHeight = musicalSymbolSizes.note.height();
        return Position.create(noteHeight, 5 * noteHeight);
    }

    Position staffStartLineBottom() {
        int noteHeight = musicalSymbolSizes.note.height();
        return Position.create(noteHeight, 9 * noteHeight);
    }
}
