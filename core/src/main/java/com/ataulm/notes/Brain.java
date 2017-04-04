package com.ataulm.notes;

public class Brain {

    private static final Callback NOOP_CALLBACK = new Callback() {
        @Override
        public void onSequenceComplete() {
            // no op
        }
    };

    private final OnSequenceUpdatedCallback onSequenceUpdatedCallback;

    private Callback callback = NOOP_CALLBACK;

    private Sequence sequence;

    Brain(OnSequenceUpdatedCallback onSequenceUpdatedCallback) {
        this.onSequenceUpdatedCallback = onSequenceUpdatedCallback;
    }

    public void attach(Callback callback) {
        this.callback = callback;
    }

    public void removeCallbacks() {
        this.callback = NOOP_CALLBACK;
    }

    public void start(Sequence sequence) {
        this.sequence = sequence;
        onSequenceUpdatedCallback.onNext(sequence);
    }

    public void onNotesPlayed(Note... notes) {
        onNotesPlayed(ConcurrentNotes.create(notes));
    }

    public void onNotesPlayed(ConcurrentNotes concurrentNotes) {
        if (concurrentNotes.isEmpty()) {
            return;
        }

        int position = sequence.position();
        ConcurrentNotes expectedNotes = sequence.get(position);
        if (concurrentNotes.equals(expectedNotes)) {
            moveToNextPositionOrComplete();
        } else {
            displayIncorrect(concurrentNotes);
        }
    }

    private void displayIncorrect(ConcurrentNotes notes) {
        Sequence updatedSequence = sequence.withIncorrect(notes);
        onSequenceUpdatedCallback.onNext(updatedSequence);
    }

    private void moveToNextPositionOrComplete() {
        int position = sequence.position();
        if (position == sequence.length() - 1) {
            callback.onSequenceComplete();
        } else {
            Sequence updatedSequence = sequence.withPositionIncremented();
            onSequenceUpdatedCallback.onNext(updatedSequence);

            this.sequence = updatedSequence;
        }
    }

    public interface Callback {

        void onSequenceComplete();
    }
}
