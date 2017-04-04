package com.ataulm.notes.legacy;

import java.util.List;

class NotesFetcher {

    // TODO: this should be random within the constraints specified by the user settings (treble vs base vs grand, key)
    public List<Note> fetchNotes() {
        return Fixture.createNotes();
    }

}
