package com.ataulm.notes;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * This class depends on knowing what the layout looks like. Makes sense if it handles size and position of different notes.
 */
class ConcurrentNotesWidgetSizeCalculator {

    private static final Comparator<Note> NOTE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            if (o1.midi() == o2.midi()) {
                return 0;
            }
            if (o1.midi() > o2.midi()) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    private final MusicalSymbolSizes symbolSizes;
    private final PositionsApartFromMiddleCCalculator positionCalculator;

    ConcurrentNotesWidgetSizeCalculator(MusicalSymbolSizes symbolSizes, PositionsApartFromMiddleCCalculator positionCalculator) {
        this.symbolSizes = symbolSizes;
        this.positionCalculator = positionCalculator;
    }

    Size size(Key key, ConcurrentNotes concurrentNotes) {
        List<Note> notes = new ArrayList<>(concurrentNotes.notes());
        Collections.sort(notes, NOTE_COMPARATOR);

        return Size.create(requiredWidth(notes), requiredHeight(key, notes));
    }

    private int requiredWidth(List<Note> notes) {
        // TODO: best case is symbolSizes.note.width()
        // also need to account for:
        // accidentals,
        // notes which are too close to each other to be displayed directly beneath each other,
        // and accidentals which are too close to each other to be displayed directly beneath each other
        return symbolSizes.note.width();
    }

    // TODO: since accidentals are generally taller than single notes, we can't rely solely on notes to determine height
    private int requiredHeight(Key key, List<Note> notes) {
        if (notes.isEmpty()) {
            return 0;
        }

        if (notes.size() == 1) {
            return symbolSizes.note.height();
        }

        int firstNotePosition = positionCalculator.positionsApartFromMiddleC(key, notes.get(0));
        int lastNotePosition = positionCalculator.positionsApartFromMiddleC(key, notes.get(notes.size() - 1));
        int difference = Math.abs(lastNotePosition - firstNotePosition);

        int foo = (difference - 1) * symbolSizes.note.height();
        int bar = difference % 2 == 0 ? 0 : (int) (0.5 * symbolSizes.note.height());
        return foo + bar;
    }

}
