package com.ataulm.notes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Bucket implements Iterable<Note> {

    private final List<Note> notes;

    Bucket(Note... notes) {
        this(Arrays.asList(notes));
    }

    private Bucket(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.iterator();
    }

    int size() {
        return notes.size();
    }

}
