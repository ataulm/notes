package com.ataulm.notes.legacy;

import java.util.List;

class OnClickNoteListener {

    private final List<NoteViewModel> noteViewModels;
    private final InputValidator inputValidator;

    public OnClickNoteListener(List<NoteViewModel> noteViewModels, InputValidator inputValidator) {
        this.noteViewModels = noteViewModels;
        this.inputValidator = inputValidator;
    }

    public void onClick(Note note) {
        Note nextTargetNote = getNextTargetNote(noteViewModels);
        boolean matches = inputValidator.matches(note, nextTargetNote);
        if (matches) {
            // mark this drawable as correct
            // check if all notes are correct and callback finished
        } else {
            // mark this drawable as incorrect and refresh
        }
    }

    private Note getNextTargetNote(List<NoteViewModel> noteViewModels) {
        for (NoteViewModel target : noteViewModels) {
            if (target.status() == NoteViewModel.Status.CORRECT) {
                continue;
            }
            return target.note();
        }
        throw new IllegalStateException("All the notes are correct - shouldn't be looking for the next target...");
    }

}