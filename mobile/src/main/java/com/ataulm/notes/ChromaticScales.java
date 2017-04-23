package com.ataulm.notes;

import java.util.HashMap;
import java.util.Map;

interface ChromaticScales {

    Map<Key, String[]> BY_KEY = new HashMap<Key, String[]>() {
        {
            BY_KEY.put(Key.C_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "A♯", "B"});
            BY_KEY.put(Key.G_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F♮", "F", "G", "G♯", "A", "A♯", "B"});
            BY_KEY.put(Key.F_MAJ, new String[]{"C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A", "B", "B♮"});
        }
    };

}
