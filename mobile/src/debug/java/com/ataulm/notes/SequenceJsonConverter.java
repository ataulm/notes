package com.ataulm.notes;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class SequenceJsonConverter {

    private final Gson gson;

    SequenceJsonConverter(Gson gson) {
        this.gson = gson;
    }

    Sequence convert(String json) {
        JsonSequence jsonSequence = gson.fromJson(json, JsonSequence.class);
        List<ConcurrentNotes> sequence = new ArrayList<>(jsonSequence.size());
        for (JsonNotes jsonNotes : jsonSequence) {
            ConcurrentNotes concurrentNotes = convert(jsonNotes);
            sequence.add(concurrentNotes);
        }
        return Sequence.create(sequence);
    }

    private ConcurrentNotes convert(JsonNotes jsonNotes) {
        List<Note> notes = new ArrayList<>(jsonNotes.size());
        for (JsonNote jsonNote : jsonNotes) {
            Note note = convert(jsonNote);
            notes.add(note);
        }
        return ConcurrentNotes.create(notes);
    }

    private Note convert(JsonNote jsonNote) {
        if (jsonNote.staff == null) {
            return Note.create(jsonNote.midi);
        } else {
            return Note.create(jsonNote.midi, Staff.matching(jsonNote.staff));
        }
    }
}

class JsonSequence extends ArrayList<JsonNotes> {
}

class JsonNotes extends ArrayList<JsonNote> {
}

class JsonNote {

    @SerializedName("midi")
    int midi;

    @Nullable
    @SerializedName("staff")
    String staff;

}
