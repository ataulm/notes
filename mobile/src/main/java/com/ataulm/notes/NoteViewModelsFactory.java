package com.ataulm.notes;

import java.util.ArrayList;
import java.util.List;

class NoteViewModelsFactory {
    public List<NoteViewModel> createNoteViewModels(List<Note> notes) {
        List<NoteViewModel> noteViewModels = new ArrayList<>(notes.size());
        for (Note note : notes) {
            NoteViewModel viewModel = new NoteViewModel(note, NoteViewModel.Status.UNIDENTIFIED);
            noteViewModels.add(viewModel);
        }
        return noteViewModels;
    }
}
