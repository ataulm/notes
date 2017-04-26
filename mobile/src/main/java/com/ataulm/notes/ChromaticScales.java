package com.ataulm.notes;

import com.ataulm.Optional;

import java.util.HashMap;
import java.util.Map;

class ChromaticScales {

    static final Map<Key, String[]> BY_KEY = new HashMap<>();

    private static final String SHARP_SIGN = "♯";
    private static final String NATURAL_SIGN = "♮";

    static {
        BY_KEY.put(Key.C_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B"});
        BY_KEY.put(Key.G_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F♮", "F", "G", "G♯", "A", "A♯", "B"});
        BY_KEY.put(Key.F_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "B", "B♮"});
    }

    static Optional<Accidental> accidental(Key key, Note note) {
        String[] scale = BY_KEY.get(key);
        String value = scale[note.midi() % scale.length];
        if (value.endsWith(SHARP_SIGN)) {
            return Optional.of(Accidental.SHARP);
        } else if (value.endsWith(NATURAL_SIGN)) {
            return Optional.of(Accidental.NATURAL);
        } else {
            return Optional.absent();
        }
    }

}
