package com.ataulm.notes;

import com.ataulm.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BrainTest {

    private static final Note C4 = Note.create(60);
    private static final Note D4 = Note.create(62);
    private static final Note E4 = Note.create(64);
    private static final Note F4 = Note.create(65);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    OnSequenceUpdatedCallback onSequenceUpdatedCallback;

    @Mock
    Brain.Callback callback;

    Brain brain;

    @Before
    public void setUp() {
        brain = new Brain(onSequenceUpdatedCallback);
    }

    @Test
    public void whenStarted_thenDisplaysUnmodifiedSequence() {
        Sequence sequence = make(C4, D4, E4);

        brain.start(sequence);

        verify(onSequenceUpdatedCallback).onNext(sequence);
    }

    @Test
    public void whenNotesPlayedCorrectly_thenIncrementsSequencePosition() {
        Sequence sequence = make(C4, D4, E4);
        brain.start(sequence);

        brain.onNotesPlayed(C4);

        ArgumentCaptor<Sequence> sequenceCaptor = ArgumentCaptor.forClass(Sequence.class);
        verify(onSequenceUpdatedCallback, times(2)).onNext(sequenceCaptor.capture());
        assertThat(sequenceCaptor.getValue().position()).isEqualTo(1);
    }

    @Test
    public void whenNotesPlayedIncorrectly_thenDoesNotIncrementSequencePosition() {
        Sequence sequence = make(C4, D4, E4);
        brain.start(sequence);

        brain.onNotesPlayed(C4);
        brain.onNotesPlayed(E4);

        ArgumentCaptor<Sequence> sequenceCaptor = ArgumentCaptor.forClass(Sequence.class);
        verify(onSequenceUpdatedCallback, times(3)).onNext(sequenceCaptor.capture());
        assertThat(sequenceCaptor.getValue().position()).isEqualTo(1);
    }

    @Test
    public void whenNotesPlayedIncorrectly_thenUpdatesLatestError() {
        Sequence sequence = make(C4, D4, E4);
        brain.start(sequence);

        brain.onNotesPlayed(D4);
        brain.onNotesPlayed(E4);

        ArgumentCaptor<Sequence> sequenceCaptor = ArgumentCaptor.forClass(Sequence.class);
        verify(onSequenceUpdatedCallback, times(3)).onNext(sequenceCaptor.capture());
        assertThat(sequenceCaptor.getValue().incorrectNotes()).isEqualTo(Optional.of(ConcurrentNotes.create(E4)));
    }

    @Test
    public void whenNotesPlayedCorrectly_thenClearsLatestError() {
        Sequence sequence = make(C4, D4, E4);
        brain.start(sequence);

        brain.onNotesPlayed(D4);
        brain.onNotesPlayed(C4);

        ArgumentCaptor<Sequence> sequenceCaptor = ArgumentCaptor.forClass(Sequence.class);
        verify(onSequenceUpdatedCallback, times(3)).onNext(sequenceCaptor.capture());
        assertThat(sequenceCaptor.getValue().incorrectNotes().isPresent()).isFalse();
    }

    @Test
    public void whenFinalNotesPlayedCorrectly_thenTriggersSequenceComplete() {
        Sequence sequence = make(C4, D4, E4);
        brain.attach(callback);
        brain.start(sequence);

        brain.onNotesPlayed(C4);
        brain.onNotesPlayed(D4);
        brain.onNotesPlayed(E4);

        verify(callback).onSequenceComplete();
    }

    @Test
    public void whenFinalNotesPlayedIncorrectly_thenDoesNotTriggerSequenceComplete() {
        Sequence sequence = make(C4, D4, E4);
        brain.attach(callback);
        brain.start(sequence);

        brain.onNotesPlayed(C4);
        brain.onNotesPlayed(D4);
        brain.onNotesPlayed(F4);

        verify(callback, never()).onSequenceComplete();
    }

    private Sequence make(Note... notes) {
        ConcurrentNotes[] concurrentNotes = new ConcurrentNotes[notes.length];
        for (int i = 0; i < notes.length; i++) {
            concurrentNotes[i] = ConcurrentNotes.create(notes[i]);
        }
        return Sequence.create(concurrentNotes);
    }
}