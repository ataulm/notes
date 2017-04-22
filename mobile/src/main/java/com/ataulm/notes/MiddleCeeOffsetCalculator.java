package com.ataulm.notes;

import java.util.HashMap;
import java.util.Map;

class MiddleCeeOffsetCalculator {

    private static final Map<Key, String[]> CHROMATIC_SCALES_FROM_C = new HashMap<>();
    private static final Note MIDDLE_C = Note.create(60);

    static {
        CHROMATIC_SCALES_FROM_C.put(Key.C_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B"});
        CHROMATIC_SCALES_FROM_C.put(Key.G_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F♮", "F", "G", "G♯", "A", "A♯", "B"});
        CHROMATIC_SCALES_FROM_C.put(Key.F_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "B", "B♮"});
    }

    int calculateOffset(Key key, Note note) {
        if (MIDDLE_C.equals(note)) {
            return 0;
        }

        if (note.isHigherThan(MIDDLE_C)) {
            return -calculateDifferenceBetween(MIDDLE_C, note, key);
        } else {
            return calculateDifferenceBetween(note, MIDDLE_C, key);
        }
    }

    private int calculateDifferenceBetween(Note lowerNote, Note higherNote, Key key) {
        String[] chromaticScale = CHROMATIC_SCALES_FROM_C.get(key);
        int difference = 0;

        String last = chromaticScale[lowerNote.midi() % chromaticScale.length];
        for (int pitch = lowerNote.midi(); pitch <= higherNote.midi(); pitch++) {
            String next = chromaticScale[pitch % chromaticScale.length];
            if (!next.startsWith(last.substring(0, 1))) {
                difference++;
            }
            last = next;
        }
        return difference;
    }

}
