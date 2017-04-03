package com.ataulm.notes;

import java.util.List;

class NotesPresenter {

    private final NotesView notesView;
    private final InputView inputView;
    private final NotesFetcher notesFetcher;
    private final NoteViewModelsFactory viewModelsFactory;
    private final InputValidator inputValidator;

    public NotesPresenter(NotesView notesView, InputView inputView, NotesFetcher notesFetcher, NoteViewModelsFactory viewModelsFactory, InputValidator inputValidator) {
        this.notesView = notesView;
        this.inputView = inputView;
        this.notesFetcher = notesFetcher;
        this.viewModelsFactory = viewModelsFactory;
        this.inputValidator = inputValidator;
    }

    public void present() {
        final List<OldNote> oldNotes = notesFetcher.fetchNotes();
        final List<NoteViewModel> noteViewModels = viewModelsFactory.createNoteViewModels(oldNotes);

        notesView.update(noteViewModels);
        inputView.update(new OnClickNoteListener(noteViewModels, inputValidator));
    }

}
