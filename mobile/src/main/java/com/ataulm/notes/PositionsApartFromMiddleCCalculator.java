package com.ataulm.notes;

import java.util.HashMap;
import java.util.Map;

class PositionsApartFromMiddleCCalculator {

    private static final Map<Key, String[]> CHROMATIC_SCALES_FROM_C = new HashMap<>();
    private static final Note MIDDLE_C = Note.create(60);

    static {
        CHROMATIC_SCALES_FROM_C.put(Key.C_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B"});
        CHROMATIC_SCALES_FROM_C.put(Key.G_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F♮", "F", "G", "G♯", "A", "A♯", "B"});
        CHROMATIC_SCALES_FROM_C.put(Key.F_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "B", "B♮"});
    }

    int positionsApartFromMiddleC(Key key, Note note) {
        if (MIDDLE_C.equals(note)) {
            return 0;
        }

        String[] notes = CHROMATIC_SCALES_FROM_C.get(key);
        if (note.isHigherThan(MIDDLE_C)) {
            return -positionsApart(MIDDLE_C.midi(), note.midi(), notes);
        } else {
            return positionsApart(note.midi(), MIDDLE_C.midi(), notes);
        }
    }

    private int positionsApart(int lowerPitch, int higherPitch, String[] notes) {
        int positionsAway = 0;

        String last = notes[lowerPitch % notes.length];
        for (int pitch = lowerPitch; pitch <= higherPitch; pitch++) {
            String next = notes[pitch % notes.length];
            if (!next.startsWith(last.substring(0, 1))) {
                positionsAway++;
            }
            last = next;
        }
        return positionsAway;
    }

}
