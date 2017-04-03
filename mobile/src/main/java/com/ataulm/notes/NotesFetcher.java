package com.ataulm.notes;

import java.util.List;

class NotesFetcher {

    // TODO: this should be random within the constraints specified by the user settings (treble vs base vs grand, key)
    public List<OldNote> fetchNotes() {
        return Fixture.createNotes();
    }

}
