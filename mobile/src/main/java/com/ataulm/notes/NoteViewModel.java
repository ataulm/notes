package com.ataulm.notes;

class NoteViewModel {

    private final OldNote oldNote;
    private final Status status;

    NoteViewModel(OldNote oldNote, Status status) {
        this.oldNote = oldNote;
        this.status = status;
    }

    public OldNote note() {
        return oldNote;
    }

    public Status status() {
        return status;
    }

    enum Status {
        UNIDENTIFIED,
        CORRECT,
        INCORRECT
    }

}
