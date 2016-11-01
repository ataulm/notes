package com.ataulm.notes;

class NoteViewModel {

    private final Note note;
    private final Status status;

    NoteViewModel(Note note, Status status) {
        this.note = note;
        this.status = status;
    }

    public Note note() {
        return note;
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
