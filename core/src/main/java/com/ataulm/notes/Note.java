package com.ataulm.notes;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Note {

    private static final int LOWEST_MIDI_NOTE = 0;
    private static final int HIGHEST_MIDI_NOTE = 127;

    public static Note create(int midi) {
        return new AutoValue_Note(checkNotOutOfRange(midi));
    }

    private static int checkNotOutOfRange(int midiNoteNumber) {
        if (midiNoteNumber < LOWEST_MIDI_NOTE && midiNoteNumber > HIGHEST_MIDI_NOTE) {
            throw new IllegalArgumentException("MIDI notes range from " + LOWEST_MIDI_NOTE + "-" + HIGHEST_MIDI_NOTE + " inclusive");
        }
        return midiNoteNumber;
    }

    public abstract int midi();

}
