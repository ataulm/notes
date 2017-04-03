package com.ataulm.notes;

import java.util.ArrayList;
import java.util.List;

class NoteViewModelsFactory {
    public List<NoteViewModel> createNoteViewModels(List<OldNote> oldNotes) {
        List<NoteViewModel> noteViewModels = new ArrayList<>(oldNotes.size());
        for (OldNote oldNote : oldNotes) {
            NoteViewModel viewModel = new NoteViewModel(oldNote, NoteViewModel.Status.UNIDENTIFIED);
            noteViewModels.add(viewModel);
        }
        return noteViewModels;
    }
}
