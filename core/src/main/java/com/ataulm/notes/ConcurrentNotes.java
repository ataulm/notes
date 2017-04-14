package com.ataulm.notes;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@AutoValue
public abstract class ConcurrentNotes implements Iterable<Note> {

    public static ConcurrentNotes create(Note... notes) {
        List<Note> notesWithoutDuplicates = asListWithNoDuplicate(notes);
        return new AutoValue_ConcurrentNotes(notesWithoutDuplicates);
    }

    private static List<Note> asListWithNoDuplicate(Note[] notes) {
        HashSet<Note> set = new HashSet<>(Arrays.asList(notes));
        return new ArrayList<>(set);
    }

    abstract List<Note> notes();

    @Override
    public Iterator<Note> iterator() {
        return notes().iterator();
    }

    public boolean isEmpty() {
        return notes().isEmpty();
    }

}
