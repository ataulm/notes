package com.ataulm.notes;

class MiddleCeeOffsetCalculator {

    private static final Note MIDDLE_C = Note.create(60);

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
        String[] scale = ChromaticScales.BY_KEY.get(key);
        int difference = 0;

        String last = scale[lowerNote.midi() % scale.length];
        for (int pitch = lowerNote.midi(); pitch <= higherNote.midi(); pitch++) {
            String next = scale[pitch % scale.length];
            if (!next.startsWith(last.substring(0, 1))) {
                difference++;
            }
            last = next;
        }
        return difference;
    }

}
